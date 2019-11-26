package eu.sesma.paraguas.ui.master

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.annotation.ColorInt
import android.text.format.Time
import android.util.TypedValue
import android.view.ViewTreeObserver
import android.widget.ImageView
import eu.sesma.paraguas.R
import eu.sesma.paraguas.domain.Astronomy
import eu.sesma.paraguas.domain.CurrentWeather
import eu.sesma.paraguas.domain.ForecastItem
import java.text.SimpleDateFormat
import javax.inject.Inject
import kotlin.math.min


class Graph
@Inject
constructor(private val context: Context) {

    companion object {
        const val FL_WIDTH = 4f
    }

    private val time = Time()
    private val temps = mutableListOf<Double>()
    private val feelLike = mutableListOf<Double>()
    private val rainsQuantity = mutableListOf<Double>()
    private val rainsProbability = mutableListOf<Double>()
    private val linePaint = Paint()

    private var imageView: ImageView? = null
    var currentWeather: CurrentWeather? = null
    var forecast: List<ForecastItem> = emptyList()
    var astronomy: Astronomy? = null

    private val layoutListener = ViewTreeObserver.OnGlobalLayoutListener { drawInternal() }

    fun draw(imageView: ImageView) {
        if (currentWeather == null || astronomy == null || forecast.isEmpty()) return

        linePaint.color = Color.BLACK
        this.imageView = imageView
        imageView.viewTreeObserver.addOnGlobalLayoutListener(layoutListener)
    }

    fun drawWidget(): Bitmap? {
        if (currentWeather == null || astronomy == null || forecast.isEmpty()) return null

        prepareData()

        linePaint.color = Color.WHITE

        val width = context.toPixels(240)
        val height = context.toPixels(80)
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        drawTemp(canvas)
        drawRain(canvas)

        return bitmap
    }

    private fun drawInternal() {
        val safeView = imageView ?: return

        safeView.viewTreeObserver?.removeOnGlobalLayoutListener(layoutListener)

        prepareData()

        val bitmap = Bitmap.createBitmap(safeView.width, safeView.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        drawTemp(canvas)
        drawRain(canvas)

        safeView.setImageBitmap(bitmap)
    }

    private fun prepareData() {
        time.setToNow()
        temps.clear()
        feelLike.clear()
        rainsQuantity.clear()
        rainsProbability.clear()

        temps.add(currentWeather?.temp ?: 0.0)
        feelLike.add(currentWeather?.feelsLike ?: 0.0)
        rainsQuantity.add(currentWeather?.precip1hrMetric ?: 0.0)
        rainsProbability.add(0.0)
        forecast.forEach {
            temps.add(it.temp)
            feelLike.add(it.feelslike)
            rainsQuantity.add(it.rainQuantity)
            rainsProbability.add(it.rainProbability)
        }
        rainsProbability[0] = if (rainsQuantity[0] > 0) 0.5 else rainsProbability[1]
    }

    private fun Context.toPixels(dp: Int) =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            resources.displayMetrics
        ).toInt()

    @SuppressLint("SimpleDateFormat")
    private fun drawTemp(canvas: Canvas) {
        val sunriseH = SimpleDateFormat("HH").format(astronomy?.sunrise).toInt()
        val sunsetH = SimpleDateFormat("HH").format(astronomy?.sunset).toInt()
        val sunriseM = SimpleDateFormat("mm").format(astronomy?.sunrise).toInt()
        val sunsetM = SimpleDateFormat("mm").format(astronomy?.sunset).toInt()
        val hours = temps.size

        val paint = Paint()

        val feelPaint = Paint()
        feelPaint.color = Color.RED
        feelPaint.strokeWidth = FL_WIDTH

        var min = 100.0
        var max = -100.0
        for (temp in temps) {
            min = Math.min(min, temp)
            max = Math.max(max, temp)
        }
        val degree = canvas.height * 0.8 / (max - min)
        val height = (canvas.height * 0.9).toFloat()
        val step: Float = 60 * canvas.width / (hours * 60 - time.minute).toFloat()
        var xpos = 0f

        for (i in 0 until hours) {
            temps[i] -= min
            feelLike[i] -= min

            var hour = time.hour + i
            if (hour > 23) hour %= 24

            val ypos = height - (temps[i] * degree).toInt()
            if (i == 0) {
                val isNight = hour < sunriseH || hour > sunsetH
                paint.color = getDaylightColor(isNight)
                canvas.drawRect(
                    xpos,
                    ypos,
                    xpos + step * (60 - time.minute) / 60,
                    canvas.height.toFloat(),
                    paint
                )
            } else if (hour == sunriseH || hour == sunsetH) {
                val minute = if (hour == sunriseH) sunriseM else sunsetM
                val isNight = hour < sunriseH || hour == sunriseH && minute < sunriseM ||
                        hour > sunsetH || hour == sunsetH && minute >= sunsetM

                val x = xpos + step * (60 - minute) / 60

                paint.color = getDaylightColor(!isNight)
                canvas.drawRect(xpos, ypos, x, canvas.height.toFloat(), paint)
                paint.color = getDaylightColor(isNight)
                canvas.drawRect(x, ypos, xpos + step, canvas.height.toFloat(), paint)
            } else {
                val isNight = hour < sunriseH || hour > sunsetH
                paint.color = getDaylightColor(isNight)
                canvas.drawRect(xpos, ypos, xpos + step, canvas.height.toFloat(), paint)
            }

            if (temps[i] != feelLike[i]) {
                val yposFeel = height - (feelLike[i] * degree).toInt() - FL_WIDTH / 2
                canvas.drawLine(xpos, yposFeel, xpos + step, yposFeel, feelPaint)
            }

            linePaint.textSize = 36f
            if (hour % 6 == 0) {
                canvas.drawText(Integer.toString(hour), xpos, 32f, linePaint)
                canvas.drawLine(xpos, 0f, xpos, canvas.height.toFloat(), linePaint)
            }

            xpos += if (i == 0) step * (60 - time.minute) / 60 else step
        }

        linePaint.textSize = 48f
        var ypos = height - ((max - min) * degree).toInt()
        canvas.drawLine(0f, ypos, canvas.width.toFloat(), ypos, linePaint)
        canvas.drawText(Integer.toString(max.toInt()), 0f, (ypos + 40), linePaint)
        ypos = height
        canvas.drawLine(0f, ypos, canvas.width.toFloat(), ypos, linePaint)
        canvas.drawText(Integer.toString(min.toInt()), 0f, (ypos - 10), linePaint)
    }

    private fun drawRain(canvas: Canvas) {
        val paint = Paint()
        val hours = temps.size
        paint.color = Color.BLUE

        val height = canvas.height
        val step: Float = 60 * canvas.width / (hours * 60 - time.minute).toFloat()
        var xpos = 0f

        (0 until hours).forEach {
            val alpha = 80 + (rainsQuantity[it] * 80).toInt()
            paint.alpha = min(alpha, 160)
            val ypos = height - (rainsProbability[it].toFloat() * 0.9f) * height
            xpos += if (it == 0) {
                canvas.drawRect(
                    xpos,
                    ypos,
                    xpos + step * (60 - time.minute) / 60,
                    canvas.height.toFloat(),
                    paint
                )
                step * (60 - time.minute) / 60
            } else {
                canvas.drawRect(xpos, ypos, xpos + step, canvas.height.toFloat(), paint)
                step
            }
        }
    }

    @ColorInt
    private fun getDaylightColor(night: Boolean) =
        if (night) context.resources.getColor(R.color.darkYellow) else Color.YELLOW
}
