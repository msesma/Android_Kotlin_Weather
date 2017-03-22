package com.paradigmadigital.peluco;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.text.format.Time;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WeatherGraph {
    private static final String TAG = WeatherGraph.class.getSimpleName();
    private static final int DATA_SETS = 25;

    private static JSONArray tempsJSONArray;
    private static JSONArray rainsQpfJSONArray;
    private static JSONArray rainsPopJSONArray;

    private ArrayList<Float> temps = new ArrayList<>();
    private ArrayList<Integer> rainsQpf = new ArrayList<>();
    private ArrayList<Integer> rainsPop = new ArrayList<>();
    private int sunriseH;
    private int sunsetH;
    private int sunriseM;
    private int sunsetM;
    private Bitmap iconBitmap;
    private SharedPreferences settings;

    private Canvas canvas;
    private Rect bounds;
    private float hours;
    private Time time = new Time();
    private long filesLastUpdateTime;
    private Context context;
    private int firstSet;

    public WeatherGraph(Context context) {
        this.context = context;
        settings = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public CurrentConditions drawWeather(Canvas canvas, Rect bounds) {
        this.canvas = canvas;
        this.bounds = bounds;
        time.setToNow();
        if (retrieveWeather()) {
            String temp = "";
            if (temps != null && !temps.isEmpty()) {
                temp = String.format("%.1f", temps.get(0));
            }
            drawTempForecast();
            drawRainForecast();
            return new CurrentConditions(iconBitmap, temp, settings.getString(WearConstants.CITY, ""));
        }
        return new CurrentConditions(null, "", "");
    }

    private boolean retrieveWeather() {
        Long lastUpdateTime = settings.getLong(WearConstants.LAST_UPDATE_TIME, System.currentTimeMillis());
        if (filesLastUpdateTime == lastUpdateTime) {
            long offset = System.currentTimeMillis() - filesLastUpdateTime;
            firstSet = (int) (offset / (TimeUnit.HOURS.toMillis(1)));
            try {
                loadFromCache();
            } catch (JSONException jse) {
                Log.d(TAG, jse.getMessage());
                return false;
            }
            hours = Math.min(DATA_SETS, temps.size() - firstSet);
            return true;
        }
        filesLastUpdateTime = lastUpdateTime;

        try {
            tempsJSONArray = new JSONArray(settings.getString(WearConstants.KEY_TEMPS, ""));
            rainsQpfJSONArray = new JSONArray(settings.getString(WearConstants.KEY_RAINS_QPF, ""));
            rainsPopJSONArray = new JSONArray(settings.getString(WearConstants.KEY_RAINS_POP, ""));
            long sunrise = settings.getLong(WearConstants.KEY_SUNRISE, 0L);
            long sunset = settings.getLong(WearConstants.KEY_SUNSET, 0L);
            Date dateSunrise = new Date(sunrise);
            sunriseH = dateSunrise.getHours();
            sunriseM = dateSunrise.getMinutes();
            Date dateSunset = new Date(sunset);
            sunsetH = dateSunset.getHours();
            sunsetM = dateSunset.getMinutes();
            iconBitmap = getIconBitmap(sunrise, sunset, settings.getString(WearConstants.ICON, ""));
            loadFromCache();
            return true;
        } catch (JSONException jse) {
            Log.d(TAG, jse.getMessage());
            return false;
        }
    }

    private void loadFromCache() throws JSONException {
        temps = jsonArrayToArrayList(tempsJSONArray);
        rainsQpf = jsonArrayToArrayList(rainsQpfJSONArray);
        rainsPop = jsonArrayToArrayList(rainsPopJSONArray);
    }

    private <T> ArrayList<T> jsonArrayToArrayList(JSONArray jsonArray) throws JSONException {
        ArrayList<T> result = new ArrayList<>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                result.add((T) jsonArray.get(i));
            }
        }
        return result;
    }

    private Bitmap getIconBitmap(long sunrise, long sunset, String iconKey) {
        int resource = new IconMapper().map(iconKey, sunrise, sunset);
        if (resource != 0) {
            return BitmapFactory.decodeResource(context.getResources(), resource);
        }
        return null;
    }

    private void drawTempForecast() {
        Paint paint = new Paint();

        paint.setStrokeWidth(10);

        int min = 100;
        int max = -100;
        for (int i = firstSet; i < firstSet + hours; i++) {
            int temp = Math.round(temps.get(i));
            min = Math.min(min, temp);
            max = Math.max(max, temp);
        }
        double degree = bounds.height() * 0.8 / (max - min);
        for (int i = firstSet; i < firstSet + hours; i++) {
            temps.add(i, temps.get(i) - min);
            temps.remove(i + 1);
        }

        float height = (int) (bounds.height() * 0.9);
        float step = bounds.width() / (hours - 1);
        float xpos = 0, ypos, yposEnd, xposHalf, yposHalf;

        Paint linePaint = new Paint();
        linePaint.setColor(Color.GRAY);
        ypos = height - (int) ((max - min) * degree) + bounds.top;
        float upperLineY = ypos;
        canvas.drawLine(40, ypos, canvas.getWidth(), ypos, linePaint);
        ypos = height + bounds.top;
        canvas.drawLine(40, ypos, canvas.getWidth(), ypos, linePaint);

        for (int i = 0; i < hours - 1; i++) {
            int hour = time.hour + i;
            if (hour > 24) {
                hour = hour - 24;
            }

            if (hour % 6 == 0) {
                canvas.drawLine(xpos, upperLineY, xpos, height + bounds.top, linePaint);
            }

            ypos = height + bounds.top - (int) (temps.get(i + firstSet) * degree);
            yposEnd = height + bounds.top - (int) (temps.get(i + firstSet + 1) * degree);

            if (hour == sunriseH || hour == sunsetH) {

                int minute = sunriseM;
                if (hour == sunsetH) {
                    minute = sunsetM;
                }

                xposHalf = xpos + step * minute / 60;
                yposHalf = ypos + (yposEnd - ypos) * minute / 60;

                setDaylightColor(paint, hour, minute - 1);
                canvas.drawLine(xpos, ypos, xposHalf, yposHalf, paint);
                setDaylightColor(paint, hour, minute);
                canvas.drawLine(xposHalf, yposHalf, xpos + step + 1, yposEnd, paint);
            } else {
                setDaylightColor(paint, hour, 0);
                canvas.drawLine(xpos, ypos, xpos + step + 1, yposEnd, paint);
            }

            xpos += step;
        }

        linePaint.setColor(Color.WHITE);
        linePaint.setTextSize(36);
        linePaint.setTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD));
        linePaint.setShadowLayer(1.0f, 1.0f, 1.0f, Color.BLACK);
        canvas.drawText(Integer.toString(max), 0, (int) (upperLineY + 18), linePaint);
        canvas.drawText(Integer.toString(min), 0, (int) (height + bounds.top + 12), linePaint);
    }

    private void setDaylightColor(Paint paint, int hour, int minute) {
        if (hour < sunriseH ||
                hour == sunriseH && minute < sunriseM ||
                hour > sunsetH ||
                hour == sunsetH && minute >= sunsetM) {
            paint.setColor(context.getResources().getColor(R.color.DARK_YELLOW));
        } else {
            paint.setColor(Color.YELLOW);
        }
    }

    private void drawRainForecast() {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);

        float degree = bounds.height() / 100;

        float height = bounds.height();
        float step = bounds.width() / hours;
        float xpos = 0;
        float ypos;

        for (int i = 0; i < hours; i++) {
            paint.setAlpha(96 + rainsQpf.get(i + firstSet) * 8);
            ypos = bounds.top + height - (rainsPop.get(i + firstSet) * degree);
            canvas.drawRect(xpos, ypos, xpos + step, canvas.getHeight(), paint);
            xpos += step;
        }
    }
}
