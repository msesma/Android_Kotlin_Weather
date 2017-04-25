package com.paradigmadigital.peluco

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.*
import android.os.BatteryManager
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.wearable.watchface.CanvasWatchFaceService
import android.support.wearable.watchface.WatchFaceStyle
import android.text.format.Time
import android.view.SurfaceHolder
import android.view.WindowInsets
import java.text.DateFormatSymbols
import java.util.*

class WatchFaceService : CanvasWatchFaceService() {

    companion object {
        private val BOLD_TYPEFACE = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
        private val NORMAL_TYPEFACE = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
        private val NORMAL_UPDATE_RATE_MS: Long = 500
        private val MSG_UPDATE_TIME = 0
        private val WEEKDAYS = DateFormatSymbols().weekdays
        private val MONTHS = DateFormatSymbols().shortMonths
        private val COLON_STRING = ":"
    }

    override fun onCreateEngine(): Engine {
        return Engine()
    }

    inner class Engine : CanvasWatchFaceService.Engine() {

        internal val updateTimeHandler: Handler = object : Handler() {
            override fun handleMessage(message: Message) {
                if (message.what == MSG_UPDATE_TIME) {
                    updateTime()
                }
            }
        }

        internal val timeZoneReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                currentTime.clear(intent.getStringExtra("time-zone"))
                currentTime.setToNow()
            }
        }

        private var registeredTimeZoneReceiver = false
        private var date = ""
        private var day = -1
        private var dateOffset = 0f

        lateinit private var backgroundPaint: Paint
        lateinit private var hourPaint: Paint
        lateinit private var minutePaint: Paint
        lateinit private var secondPaint: Paint
        lateinit private var datePaint: Paint
        lateinit private var cityPaint: Paint
        lateinit private var currentTempPaint: Paint
        lateinit private var batteryPaint: Paint
        lateinit private var colonPaint: Paint

        private var colonWidth: Float = 0f
        lateinit private var currentTime: Time
        private var yOffset: Float = 0f
        private var yStep: Float = 0f
        private var cityYOffset: Float = 0f
        private val cardBounds = Rect()

        private var weatherGraph: WeatherGraph = WeatherGraph(this@WatchFaceService)
        private var lowBitAmbient: Boolean = false

        override fun onCreate(holder: SurfaceHolder) {
            super.onCreate(holder)

            setWatchFaceStyle(WatchFaceStyle.Builder(this@WatchFaceService)
                    .setCardPeekMode(WatchFaceStyle.PEEK_MODE_VARIABLE)
                    .setBackgroundVisibility(WatchFaceStyle.BACKGROUND_VISIBILITY_INTERRUPTIVE)
                    .setShowSystemUiTime(false)
                    .setPeekOpacityMode(WatchFaceStyle.PEEK_OPACITY_MODE_OPAQUE)
                    .setHideHotwordIndicator(true)
                    .build())

            initializeValues()
        }

        override fun onPropertiesChanged(properties: Bundle) {
            super.onPropertiesChanged(properties)
            hourPaint.typeface = BOLD_TYPEFACE
            lowBitAmbient = properties.getBoolean(PROPERTY_LOW_BIT_AMBIENT, false)
        }

        override fun onTimeTick() {
            super.onTimeTick()
            invalidate()
        }

        override fun onAmbientModeChanged(inAmbientMode: Boolean) {
            super.onAmbientModeChanged(inAmbientMode)
            if (lowBitAmbient) {
                setLowBitAmbientValues(inAmbientMode)
            }
            invalidate()
            updateTimer()
        }

        override fun onDraw(canvas: Canvas, bounds: Rect) {
            currentTime.setToNow()

            canvas.drawRect(0f, 0f, bounds.width().toFloat(), bounds.height().toFloat(), backgroundPaint)
            drawTime(canvas)
            canvas.drawText(date, dateOffset, yOffset + yStep, datePaint)
            drawCurrentConditions(canvas, bounds)
            drawBatteryState(canvas, bounds)
            drawCardBounds(canvas)
        }

        override fun onVisibilityChanged(visible: Boolean) {
            super.onVisibilityChanged(visible)

            if (visible) {
                registerReceiver()
                currentTime.clear(TimeZone.getDefault().id)
                currentTime.setToNow()
            } else {
                unregisterReceiver()
            }
            updateTimer()
        }

        override fun onDestroy() {
            updateTimeHandler.removeMessages(MSG_UPDATE_TIME)
            super.onDestroy()
        }

        override fun onApplyWindowInsets(insets: WindowInsets) {
            super.onApplyWindowInsets(insets)
            initializeInsets(insets)
        }

        private fun drawBatteryState(canvas: Canvas, bounds: Rect) {
            if (isInAmbientMode) return

            val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
            val batteryStatus = this@WatchFaceService.registerReceiver(null, filter)
            val level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            val batteryPct = level / scale.toFloat()
            batteryPaint.color = if (level > 15) Color.GREEN else Color.RED

            canvas.drawLine((bounds.right - 1).toFloat(), bounds.bottom.toFloat(), (bounds.right - 1).toFloat(),
                    bounds.bottom - bounds.bottom * batteryPct, batteryPaint)
        }

        private fun drawCurrentConditions(canvas: Canvas, bounds: Rect) {
            val yWeatherOffset = (yOffset + yStep * 1.5).toInt()
            val rect = Rect(0, yWeatherOffset, canvas.width, canvas.height)
            val currentConditions = weatherGraph.drawWeather(canvas, rect)

            if (currentConditions.icon != null) {
                canvas.drawBitmap(currentConditions.icon, ((bounds.width() / 2)-20).toFloat(), 0f, Paint())
                canvas.drawText(currentConditions.temp, ((bounds.width() * 3 / 4)-20).toFloat(), yStep * 1.5f, currentTempPaint)
                val xOffset = (canvas.width - cityPaint.measureText(currentConditions.city)) / 2
                canvas.drawText(currentConditions.city, xOffset, cityYOffset, cityPaint)
            }
        }

        private fun drawTime(canvas: Canvas) {
            val shouldDrawColons = System.currentTimeMillis() % 1000 < 500
            val hourString = currentTime.hour.toString()
            val hourWidth = hourPaint.measureText(hourString)
            val minuteString = formatTwoDigitNumber(currentTime.minute)
            val minuteWidth = minutePaint.measureText(minuteString)

            var secondString = ""
            var secondsWidth = 0f
            if (!isInAmbientMode) {
                secondString = formatTwoDigitNumber(currentTime.second)
                secondsWidth = colonWidth + secondPaint.measureText(secondString)
            }

            var x = (canvas.width.toFloat() - hourWidth - colonWidth - minuteWidth - secondsWidth) / 2
            canvas.drawText(hourString, x, yOffset, hourPaint)
            x += hourWidth

            if (shouldDrawColons) canvas.drawText(COLON_STRING, x, yOffset, colonPaint)
            x += colonWidth

            canvas.drawText(minuteString, x, yOffset, minutePaint)
            x += minuteWidth

            if (!isInAmbientMode) {
                if (shouldDrawColons) canvas.drawText(COLON_STRING, x, yOffset, colonPaint)
                x += colonWidth
                canvas.drawText(secondString, x, yOffset, secondPaint)
            }

            if (currentTime.yearDay != day) {
                day = currentTime.yearDay
                val weekDay = currentTime.weekDay + 1
                date = WEEKDAYS[weekDay] + " " + currentTime.monthDay + " " + MONTHS[currentTime.month]
                dateOffset = (canvas.width - datePaint.measureText(date)) / 2
            }
        }

        private fun drawCardBounds(canvas: Canvas) {
            if (isInAmbientMode) {
                backgroundPaint.color = Color.WHITE
                canvas.drawRect((cardBounds.left - 1).toFloat(),
                        (cardBounds.top - 1).toFloat(),
                        (cardBounds.right + 1).toFloat(),
                        (cardBounds.bottom + 1).toFloat(), backgroundPaint)
                backgroundPaint.color = Color.BLACK
                canvas.drawRect(cardBounds.left.toFloat(),
                        cardBounds.top.toFloat(),
                        cardBounds.right.toFloat(),
                        cardBounds.bottom.toFloat(), backgroundPaint)
            }
        }

        private fun updateTime() {
            invalidate()
            if (shouldTimerBeRunning()) {
                val timeMs = System.currentTimeMillis()
                val delayMs = NORMAL_UPDATE_RATE_MS - timeMs % NORMAL_UPDATE_RATE_MS
                updateTimeHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIME, delayMs)
            }
        }

        private fun updateTimer() {
            updateTimeHandler.removeMessages(MSG_UPDATE_TIME)
            if (shouldTimerBeRunning()) updateTimeHandler.sendEmptyMessage(MSG_UPDATE_TIME)

        }

        private fun formatTwoDigitNumber(hour: Int): String {
            return String.format("%02d", hour)
        }

        private fun shouldTimerBeRunning(): Boolean {
            return isVisible && !isInAmbientMode
        }

        private fun registerReceiver() {
            if (registeredTimeZoneReceiver) return

            registeredTimeZoneReceiver = true
            val filter = IntentFilter(Intent.ACTION_TIMEZONE_CHANGED)
            this@WatchFaceService.registerReceiver(timeZoneReceiver, filter)
        }

        private fun unregisterReceiver() {
            if (!registeredTimeZoneReceiver) return

            registeredTimeZoneReceiver = false
            this@WatchFaceService.unregisterReceiver(timeZoneReceiver)
        }

        private fun initializeValues() {
            val resources = this@WatchFaceService.resources
            yOffset = resources.getDimension(R.dimen.digital_y_offset)
            yStep = resources.getDimension(R.dimen.digital_y_step)
            cityYOffset = yOffset - 2.5f * yStep
            backgroundPaint = Paint()
            backgroundPaint.color = Color.BLACK
            hourPaint = createTextPaint(Color.WHITE, BOLD_TYPEFACE)
            minutePaint = createTextPaint(Color.WHITE, BOLD_TYPEFACE)
            secondPaint = createTextPaint(Color.WHITE)
            cityPaint = createTextPaint(Color.LTGRAY, NORMAL_TYPEFACE)
            currentTempPaint = createTextPaint(Color.LTGRAY, BOLD_TYPEFACE)
            datePaint = createTextPaint(Color.GREEN, BOLD_TYPEFACE)
            batteryPaint = Paint()
            colonPaint = createTextPaint(resources.getColor(R.color.digital_colons))
            currentTime = Time()
        }

        private fun initializeInsets(insets: WindowInsets) {
            val resources = this@WatchFaceService.resources
            val isRound = insets.isRound
            val textSize = resources
                    .getDimension(if (isRound) R.dimen.digital_text_size_round else R.dimen.digital_text_size)
            val dateSize = resources
                    .getDimension(if (isRound) R.dimen.digital_date_size_round else R.dimen.digital_date_size)
            val tzSize = resources.getDimension(if (isRound) R.dimen.digital_tz_size_round else R.dimen.digital_tz_size)
            hourPaint.textSize = textSize
            minutePaint.textSize = textSize
            secondPaint.textSize = textSize * 0.8f
            cityPaint.textSize = tzSize
            currentTempPaint.textSize = tzSize * 1.4f
            datePaint.textSize = dateSize
            colonPaint.textSize = textSize
            colonWidth = colonPaint.measureText(COLON_STRING)
        }

        private fun setLowBitAmbientValues(inAmbientMode: Boolean) {
            val antiAlias = !inAmbientMode
            hourPaint.isAntiAlias = antiAlias
            minutePaint.isAntiAlias = antiAlias
            secondPaint.isAntiAlias = antiAlias
            cityPaint.isAntiAlias = antiAlias
            cityPaint.color = if (inAmbientMode) Color.WHITE else Color.LTGRAY
            datePaint.isAntiAlias = antiAlias
            datePaint.color = if (inAmbientMode) Color.WHITE else Color.GREEN
            colonPaint.isAntiAlias = antiAlias
        }

        private fun createTextPaint(defaultInteractiveColor: Int, typeface: Typeface = NORMAL_TYPEFACE): Paint {
            val paint = Paint()
            paint.color = defaultInteractiveColor
            paint.typeface = typeface
            paint.isAntiAlias = true
            return paint
        }
    }

}
