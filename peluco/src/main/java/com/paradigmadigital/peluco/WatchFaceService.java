package com.paradigmadigital.peluco;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.wearable.watchface.CanvasWatchFaceService;
import android.support.wearable.watchface.WatchFaceStyle;
import android.text.format.Time;
import android.view.SurfaceHolder;
import android.view.WindowInsets;

import java.text.DateFormatSymbols;
import java.util.TimeZone;

public class WatchFaceService extends CanvasWatchFaceService {
    private static final Typeface BOLD_TYPEFACE = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
    private static final Typeface NORMAL_TYPEFACE = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL);

    private static final long NORMAL_UPDATE_RATE_MS = 500;
    private final static String[] WEEKDAYS = new DateFormatSymbols().getWeekdays();
    private final static String[] MONTHS = new DateFormatSymbols().getShortMonths();

    @Override
    public Engine onCreateEngine() {
        return new Engine();
    }

    private class Engine extends CanvasWatchFaceService.Engine {

        private static final String COLON_STRING = ":";
         private static final int MSG_UPDATE_TIME = 0;
        private long mInteractiveUpdateRateMs = NORMAL_UPDATE_RATE_MS;

        final Handler mUpdateTimeHandler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                if (message.what == MSG_UPDATE_TIME) {
                    updateTime();
                }
            }
        };

        final BroadcastReceiver mTimeZoneReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                currentTime.clear(intent.getStringExtra("time-zone"));
                currentTime.setToNow();
            }
        };

        private boolean mRegisteredTimeZoneReceiver = false;
        private String date = "";
        private int day = -1;
        private float dateOffset = 0;

        private Paint mBackgroundPaint;
        private Paint mHourPaint;
        private Paint mMinutePaint;
        private Paint mSecondPaint;
        private Paint mDatePaint;
        private Paint cityPaint;
        private Paint currentTempPaint;
        private Paint batteryPaint;
        private Paint mColonPaint;

        private float mColonWidth;
        private Time currentTime;
        private float mYOffset;
        private float mYStep;
        private float cityYOffset;
        private final Rect mCardBounds = new Rect();

        private WeatherGraph weatherGraph;
        private boolean mLowBitAmbient;

        @Override
        public void onCreate(SurfaceHolder holder) {
            super.onCreate(holder);

            setWatchFaceStyle(new WatchFaceStyle.Builder(WatchFaceService.this)
                    .setCardPeekMode(WatchFaceStyle.PEEK_MODE_VARIABLE)
                    .setBackgroundVisibility(WatchFaceStyle.BACKGROUND_VISIBILITY_INTERRUPTIVE)
                    .setShowSystemUiTime(false)
                    .setPeekOpacityMode(WatchFaceStyle.PEEK_OPACITY_MODE_OPAQUE)
                    .build());

            initializeValues();
        }

        @Override
        public void onPeekCardPositionUpdate(Rect bounds) {
            super.onPeekCardPositionUpdate(bounds);
            if (!bounds.equals(mCardBounds)) {
                mCardBounds.set(bounds);
                invalidate();
            }
        }

        @Override
        public void onDestroy() {
            mUpdateTimeHandler.removeMessages(MSG_UPDATE_TIME);
            super.onDestroy();
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);

            if (visible) {
                registerReceiver();
                currentTime.clear(TimeZone.getDefault().getID());
                currentTime.setToNow();
            } else {
                unregisterReceiver();
            }
            updateTimer();
        }

        @Override
        public void onApplyWindowInsets(WindowInsets insets) {
            super.onApplyWindowInsets(insets);
            initializeInsets(insets);
        }

        @Override
        public void onPropertiesChanged(Bundle properties) {
            super.onPropertiesChanged(properties);
            mHourPaint.setTypeface(BOLD_TYPEFACE);
            mLowBitAmbient = properties.getBoolean(PROPERTY_LOW_BIT_AMBIENT, false);
        }

        @Override
        public void onTimeTick() {
            super.onTimeTick();
            invalidate();
        }

        @Override
        public void onAmbientModeChanged(boolean inAmbientMode) {
            super.onAmbientModeChanged(inAmbientMode);
            if (mLowBitAmbient) {
                setLowBitAmbientValues(inAmbientMode);
            }
            invalidate();
            updateTimer();
        }

        @Override
        public void onInterruptionFilterChanged(int interruptionFilter) {
            super.onInterruptionFilterChanged(interruptionFilter);
        }

        @Override
        public void onDraw(Canvas canvas, Rect bounds) {
            currentTime.setToNow();

            canvas.drawRect(0, 0, bounds.width(), bounds.height(), mBackgroundPaint);
            drawTime(canvas);
            canvas.drawText(date, dateOffset, mYOffset + mYStep, mDatePaint);
            drawCurrentConditions(canvas, bounds);
            drawBatteryState(canvas, bounds);
            drawCardBounds(canvas);
        }

        private void drawBatteryState(Canvas canvas, Rect bounds) {
            if (isInAmbientMode()) {
                return;
            }
            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = WatchFaceService.this.registerReceiver(null, ifilter);
            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            float batteryPct = level / (float) scale;
            batteryPaint.setColor(level > 15 ? Color.GREEN : Color.RED);

            canvas.drawLine(bounds.right - 1, bounds.bottom, bounds.right - 1,
                    bounds.bottom - (bounds.bottom * batteryPct),
                    batteryPaint);
        }

        private void drawCurrentConditions(Canvas canvas, Rect bounds) {
            int yWeatherOffset = (int) (mYOffset + mYStep * 1.5);
            Rect rect = new Rect(0, yWeatherOffset, canvas.getWidth(), canvas.getHeight());
            CurrentConditions currentConditions = weatherGraph.drawWeather(canvas, rect);

            if (currentConditions.getIcon() != null) {
                canvas.drawBitmap(currentConditions.getIcon(), bounds.width() / 2, 0, new Paint());

                canvas.drawText(currentConditions.getTemp(), bounds.width() * 3 / 4, mYStep * 1.5f, currentTempPaint);

                float xOffset = (canvas.getWidth() - cityPaint.measureText(currentConditions.getCity())) / 2;
                canvas.drawText(currentConditions.getCity(), xOffset, cityYOffset, cityPaint);
            }
        }

        private void drawTime(Canvas canvas) {
            boolean shouldDrawColons = (System.currentTimeMillis() % 1000) < 500;
            String hourString = String.valueOf(currentTime.hour);
            float hourWidth = mHourPaint.measureText(hourString);
            String minuteString = formatTwoDigitNumber(currentTime.minute);
            float minuteWidth = mMinutePaint.measureText(minuteString);

            String secondString = "";
            float secondsWidth = 0;
            boolean isFullTimeMode = !isInAmbientMode();
            if (isFullTimeMode) {
                secondString = formatTwoDigitNumber(currentTime.second);
                secondsWidth = mColonWidth + mSecondPaint.measureText(secondString);
            }

            float x = (canvas.getWidth() - hourWidth - mColonWidth - minuteWidth - secondsWidth) / 2;
            canvas.drawText(hourString, x, mYOffset, mHourPaint);
            x += hourWidth;

            if (shouldDrawColons) {
                canvas.drawText(COLON_STRING, x, mYOffset, mColonPaint);
            }
            x += mColonWidth;

            canvas.drawText(minuteString, x, mYOffset, mMinutePaint);
            x += minuteWidth;

            if (isFullTimeMode) {
                if (shouldDrawColons) {
                    canvas.drawText(COLON_STRING, x, mYOffset, mColonPaint);
                }
                x += mColonWidth;
                canvas.drawText(secondString, x, mYOffset, mSecondPaint);
            }

            if (currentTime.yearDay != day) {
                day = currentTime.yearDay;

                int weekDay = currentTime.weekDay + 1;
                date = WEEKDAYS[weekDay] + " " + currentTime.monthDay + " " + MONTHS[currentTime.month];
                dateOffset = (canvas.getWidth() - mDatePaint.measureText(date)) / 2;
            }
        }

        private void drawCardBounds(Canvas canvas) {
            if (isInAmbientMode()) {
                mBackgroundPaint.setColor(Color.WHITE);
                canvas.drawRect(mCardBounds.left - 1,
                        mCardBounds.top - 1,
                        mCardBounds.right + 1,
                        mCardBounds.bottom + 1, mBackgroundPaint);
                mBackgroundPaint.setColor(Color.BLACK);
                canvas.drawRect(mCardBounds.left - 0,
                        mCardBounds.top - 0,
                        mCardBounds.right + 0,
                        mCardBounds.bottom + 0, mBackgroundPaint);
            }
        }

        private void initializeValues() {
            Resources resources = WatchFaceService.this.getResources();
            mYOffset = resources.getDimension(R.dimen.digital_y_offset);
            mYStep = resources.getDimension(R.dimen.digital_y_step);
            cityYOffset = mYOffset - 2.5f * mYStep;

            mBackgroundPaint = new Paint();
            mBackgroundPaint.setColor(Color.BLACK);
            mHourPaint = createTextPaint(Color.WHITE, BOLD_TYPEFACE);
            mMinutePaint = createTextPaint(Color.WHITE, BOLD_TYPEFACE);
            mSecondPaint = createTextPaint(Color.WHITE);
            cityPaint = createTextPaint(Color.LTGRAY, NORMAL_TYPEFACE);
            currentTempPaint = createTextPaint(Color.LTGRAY, BOLD_TYPEFACE);
            mDatePaint = createTextPaint(Color.GREEN, BOLD_TYPEFACE);
            batteryPaint = new Paint();
            mColonPaint = createTextPaint(resources.getColor(R.color.digital_colons));
            currentTime = new Time();
            weatherGraph = new WeatherGraph(WatchFaceService.this);
        }

        private void initializeInsets(WindowInsets insets) {
            Resources resources = WatchFaceService.this.getResources();
            boolean isRound = insets.isRound();
            float textSize = resources
                    .getDimension(isRound ? R.dimen.digital_text_size_round : R.dimen.digital_text_size);
            float dateSize = resources
                    .getDimension(isRound ? R.dimen.digital_date_size_round : R.dimen.digital_date_size);
            float tzSize = resources.getDimension(isRound ? R.dimen.digital_tz_size_round : R.dimen.digital_tz_size);
            mHourPaint.setTextSize(textSize);
            mMinutePaint.setTextSize(textSize);
            mSecondPaint.setTextSize(textSize * 0.8f);
            cityPaint.setTextSize(tzSize);
            currentTempPaint.setTextSize(tzSize * 1.4f);
            mDatePaint.setTextSize(dateSize);
            mColonPaint.setTextSize(textSize);

            mColonWidth = mColonPaint.measureText(COLON_STRING);
        }

        private void setLowBitAmbientValues(boolean inAmbientMode) {
            boolean antiAlias = !inAmbientMode;
            mHourPaint.setAntiAlias(antiAlias);
            mMinutePaint.setAntiAlias(antiAlias);
            mSecondPaint.setAntiAlias(antiAlias);
            cityPaint.setAntiAlias(antiAlias);
            cityPaint.setColor(inAmbientMode ? Color.WHITE : Color.LTGRAY);
            mDatePaint.setAntiAlias(antiAlias);
            mDatePaint.setColor(inAmbientMode ? Color.WHITE : Color.GREEN);
            mColonPaint.setAntiAlias(antiAlias);
        }

        private void updateTime() {
            invalidate();
            if (shouldTimerBeRunning()) {
                long timeMs = System.currentTimeMillis();
                long delayMs = mInteractiveUpdateRateMs - (timeMs % mInteractiveUpdateRateMs);
                mUpdateTimeHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIME, delayMs);
            }
        }

        private void updateTimer() {
            mUpdateTimeHandler.removeMessages(MSG_UPDATE_TIME);
            if (shouldTimerBeRunning()) {
                mUpdateTimeHandler.sendEmptyMessage(MSG_UPDATE_TIME);
            }
        }

        private String formatTwoDigitNumber(int hour) {
            return String.format("%02d", hour);
        }

        private boolean shouldTimerBeRunning() {
            return isVisible() && !isInAmbientMode();
        }

        private Paint createTextPaint(int defaultInteractiveColor) {
            return createTextPaint(defaultInteractiveColor, NORMAL_TYPEFACE);
        }

        private Paint createTextPaint(int defaultInteractiveColor, Typeface typeface) {
            Paint paint = new Paint();
            paint.setColor(defaultInteractiveColor);
            paint.setTypeface(typeface);
            paint.setAntiAlias(true);
            return paint;
        }

        private void registerReceiver() {
            if (mRegisteredTimeZoneReceiver) {
                return;
            }
            mRegisteredTimeZoneReceiver = true;
            IntentFilter filter = new IntentFilter(Intent.ACTION_TIMEZONE_CHANGED);
            WatchFaceService.this.registerReceiver(mTimeZoneReceiver, filter);
        }

        private void unregisterReceiver() {
            if (!mRegisteredTimeZoneReceiver) {
                return;
            }
            mRegisteredTimeZoneReceiver = false;
            WatchFaceService.this.unregisterReceiver(mTimeZoneReceiver);
        }
    }
}
