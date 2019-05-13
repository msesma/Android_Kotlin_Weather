package eu.sesma.peluco

import android.content.Context
import android.content.SharedPreferences
import android.graphics.*
import android.preference.PreferenceManager
import android.text.format.Time
import org.json.JSONArray
import org.json.JSONException
import java.util.*
import java.util.concurrent.TimeUnit

class WeatherGraph(private val context: Context) {
    companion object {
        private val DATA_SETS = 25
    }

    private lateinit var temps: List<Int>
    private lateinit var adjustedTemps: List<Int>
    private lateinit var rainsQpf: List<Float>
    private lateinit var rainsPop: List<Float>
    private var minTemp = 0
    private var maxTemp = 0
    private var willRain = false
    private var sunriseH = 0
    private var sunsetH = 0
    private var sunriseM = 0
    private var sunsetM = 0
    private var iconBitmap: Bitmap? = null
    private val settings: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    private lateinit var canvas: Canvas
    private lateinit var bounds: Rect
    private var hours = 0
    private val time = Time()
    private var filesLastUpdateTime = -1L
    private var firstSet = 0

    private val rainPaint = Paint()
    private val linePaint = Paint()
    private val tempPaint = Paint()
    private val numberPaint = Paint()

    private var height = 0f
    private var rainStep = 0f
    private var tempDegree = 0f
    private var tempStep = 0f
    private val NIGHT_COLOR = context.resources.getColor(R.color.DARK_YELLOW)
    private val DAY_COLOR = Color.YELLOW

    init {
        rainPaint.color = Color.BLUE
        linePaint.color = Color.GRAY
        tempPaint.strokeWidth = 10f
        numberPaint.color = Color.WHITE
        numberPaint.textSize = 36f
        numberPaint.typeface = Typeface.create(Typeface.MONOSPACE, Typeface.BOLD)
        numberPaint.setShadowLayer(1.0f, 1.0f, 1.0f, Color.BLACK)
    }

    fun drawWeather(canvas: Canvas, bounds: Rect): CurrentConditions {
        if (!configureData()) return CurrentConditions(null, "", "")

        this.canvas = canvas
        this.bounds = bounds
        time.setToNow()
        height = bounds.height() * 0.9f
        rainStep = bounds.width().toFloat() / (hours - 1)
        tempDegree = height / (maxTemp - minTemp)
        tempStep = bounds.width().toFloat() / (hours - 1)
        val temp = if (!temps.isEmpty()) String.format("%.1f", temps[0] / 10f) else ""

        drawTempForecast()
        if (willRain) drawRainForecast()

        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        return CurrentConditions(iconBitmap, temp, settings.getString(WearConstants.CITY, ""))
    }

    private fun configureData(): Boolean {
        val lastUpdateTime = settings.getLong(WearConstants.LAST_UPDATE_TIME, System.currentTimeMillis())
        if (filesLastUpdateTime != lastUpdateTime) {
            if (!loadStoredValues()) return false
            filesLastUpdateTime = lastUpdateTime
        }

        val offset = System.currentTimeMillis() - filesLastUpdateTime
        firstSet = (offset / TimeUnit.HOURS.toMillis(1)).toInt()
        hours = Math.min(DATA_SETS, temps.size - firstSet)
        return true
    }

    private fun drawTempForecast() {
        var xpos = 0f
        var ypos = height - ((maxTemp - minTemp) * tempDegree).toInt() + bounds.top
        val upperLineY = ypos
        canvas.drawLine(40f, ypos, canvas.width.toFloat(), ypos, linePaint)
        ypos = height + bounds.top
        canvas.drawLine(40f, ypos, canvas.width.toFloat(), ypos, linePaint)

        for (i in 0 until hours) {
            var hour = time.hour + i
            if (hour > 24) hour -= 24
            if (hour % 6 == 0) canvas.drawLine(xpos, upperLineY, xpos, height + bounds.top, linePaint)

//            canvas.drawLine(xpos, upperLineY, xpos, height + bounds.top, linePaint)

            ypos = height + bounds.top - (adjustedTemps[i + firstSet] * tempDegree) + tempDegree
            val yposEnd =
                height + bounds.top - (adjustedTemps[i + firstSet + 1] * tempDegree) + tempDegree

            if (hour == sunriseH || hour == sunsetH) {
                val minute = if (hour == sunsetH) sunsetM else sunriseM
                val xposHalf = xpos + tempStep * (60 - minute) / 60
                val yposHalf = ypos + (yposEnd - ypos) * (60 - minute) / 60

                setDaylightColor(tempPaint, hour, minute - 1)
                canvas.drawLine(xpos, ypos, xposHalf, yposHalf, tempPaint)
                setDaylightColor(tempPaint, hour, minute)
                canvas.drawLine(xposHalf, yposHalf, xpos + tempStep + 1, yposEnd, tempPaint)
            } else {
                setDaylightColor(tempPaint, hour, 0)
                canvas.drawLine(xpos, ypos, xpos + tempStep + 1, yposEnd, tempPaint)
            }

            xpos += tempStep
        }

        canvas.drawText(Integer.toString(maxTemp), 0f, (upperLineY + 18).toInt().toFloat(), numberPaint)
        canvas.drawText(
            Integer.toString(minTemp),
            0f,
            (height + bounds.top.toFloat() + 12f).toInt().toFloat(),
            numberPaint
        )
    }

    private fun setDaylightColor(paint: Paint, hour: Int, minute: Int) {
        paint.color = if (hour < sunriseH ||
            hour == sunriseH && minute < sunriseM ||
            hour > sunsetH ||
            hour == sunsetH && minute >= sunsetM
        )
            NIGHT_COLOR
        else
            DAY_COLOR
    }

    private fun drawRainForecast() {
        var xpos = 0f
        var ypos: Float

        val offset = bounds.top + height

        for (i in 0 until hours) {
            rainPaint.alpha = 96 + (rainsQpf[i + firstSet] * 80).toInt()
            ypos = offset - rainsPop[i + firstSet] * height

            canvas.drawRect(xpos, ypos, xpos + rainStep, height + bounds.top, rainPaint)
            xpos += rainStep
        }
    }

    private fun loadStoredValues(): Boolean {
        try {
            val tempsJSONArray = JSONArray(settings.getString(WearConstants.KEY_TEMPS, ""))
            val rainsQpfJSONArray = JSONArray(settings.getString(WearConstants.KEY_RAINS_QPF, ""))
            val rainsPopJSONArray = JSONArray(settings.getString(WearConstants.KEY_RAINS_POP, ""))

            temps = jsonArrayToIntArrayList(tempsJSONArray)

//            temps = listOf(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26).map { it * 10 }
//            temps = listOf(0,0,2,2,4,4,6,6,8,8,10,10,12,12,14,14,16,16,18,18,20,20,22,22,24,24,26).map { it * 10 }

            val toIndex = if (temps.size >= firstSet + DATA_SETS) firstSet + DATA_SETS else temps.size
            minTemp = (temps.subList(fromIndex = firstSet, toIndex = toIndex).min() ?: -500) / 10
            maxTemp = (temps.subList(fromIndex = firstSet, toIndex = toIndex).max() ?: 500) / 10
            adjustedTemps = temps.map { Math.round(it.toFloat() / 10) - minTemp }

//            Log.d ("===>", "Temps: FirstSet: $firstSet, minTemp $minTemp, maxTemp $maxTemp ,toIndex $toIndex ")
//            Log.d ("===>", "Temps: temps:         $temps ")
//            Log.d ("===>", "Temps: adjustedTemps: $adjustedTemps ")

            rainsQpf = jsonArrayToFloatArrayList(rainsQpfJSONArray).map { it / 10 }
            rainsPop = jsonArrayToFloatArrayList(rainsPopJSONArray).map { it / 10 }
            willRain = rainsPop.any { it > 0 }
        } catch (jse: JSONException) {
            return false
        }

        val sunrise = settings.getLong(WearConstants.KEY_SUNRISE, 0L)
        val sunset = settings.getLong(WearConstants.KEY_SUNSET, 0L)
        val dateSunrise = Date(sunrise)
        sunriseH = dateSunrise.hours
        sunriseM = dateSunrise.minutes
        val dateSunset = Date(sunset)
        sunsetH = dateSunset.hours
        sunsetM = dateSunset.minutes

//        Log.d ("===>", "Daylight $sunriseH:$sunriseM to $sunsetH:$sunsetM")

        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        iconBitmap = getIconBitmap(settings.getString(WearConstants.ICON, ""))

        return true
    }

    @Throws(JSONException::class)
    private fun jsonArrayToIntArrayList(jsonArray: JSONArray?): List<Int> {
        val result = mutableListOf<Int>()
        if (jsonArray != null) {
            for (i in 0 until jsonArray.length()) {
                result.add(jsonArray.get(i) as Int)
            }
        }
        return result
    }

    @Throws(JSONException::class)
    private fun jsonArrayToFloatArrayList(jsonArray: JSONArray?): List<Float> {
        val result = mutableListOf<Float>()
        if (jsonArray != null) {
            for (i in 0 until jsonArray.length()) {
                result.add(jsonArray.get(i) as Int / 10f)
            }
        }
        return result
    }

    private fun getIconBitmap(iconKey: String): Bitmap? {
        val resource = IconMapper().map(iconKey)
        if (resource != 0) {
            return BitmapFactory.decodeResource(context.resources, resource)
        }
        return null
    }

}
