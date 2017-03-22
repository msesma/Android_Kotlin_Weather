/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.paradigmadigital.peluco;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.WearableListenerService;

import org.json.JSONArray;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class UpdateService extends WearableListenerService {
    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        for (DataEvent dataEvent : dataEvents) {
            if (dataEvent.getType() == DataEvent.TYPE_CHANGED
                    && WearConstants.WATCH_SET_FORECAST_PATH.equals(dataEvent.getDataItem().getUri().getPath())) {
                DataMap dataMap = DataMapItem.fromDataItem(dataEvent.getDataItem()).getDataMap();
                ArrayList<String> temps = dataMap.getStringArrayList(WearConstants.KEY_TEMPS);
                ArrayList<Integer> rainsQpf = dataMap.getIntegerArrayList(WearConstants.KEY_RAINS_QPF);
                ArrayList<Integer> rainsPop = dataMap.getIntegerArrayList(WearConstants.KEY_RAINS_POP);
                long sunrise = dataMap.getLong(WearConstants.KEY_SUNRISE);
                long sunset = dataMap.getLong(WearConstants.KEY_SUNSET);
                String city = dataMap.getString(WearConstants.CITY);
                String icon = dataMap.getString(WearConstants.ICON);
                persistWeatherData(temps, rainsQpf, rainsPop, sunrise, sunset, city, icon);
            }
        }
    }

    private void persistWeatherData(
            List<String> temps,
            List<Integer> rainsQpf,
            List<Integer> rainsPop,
            long sunrise,
            long sunset,
            String city,
            String icon) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();

        JSONArray tempsJSONArray = new JSONArray(temps);
        JSONArray rainsQpfJSONArray = new JSONArray(rainsQpf);
        JSONArray rainsPopJSONArray = new JSONArray(rainsPop);

        editor.putString(WearConstants.KEY_TEMPS, tempsJSONArray.toString());
        editor.putString(WearConstants.KEY_RAINS_QPF, rainsQpfJSONArray.toString());
        editor.putString(WearConstants.KEY_RAINS_POP, rainsPopJSONArray.toString());
        editor.putLong(WearConstants.KEY_SUNRISE, sunrise);
        editor.putLong(WearConstants.KEY_SUNSET, sunset);
        editor.putString(WearConstants.CITY, city);
        editor.putString(WearConstants.ICON, icon);
        editor.putLong(WearConstants.LAST_UPDATE_TIME, System.currentTimeMillis());

        editor.apply();
    }
}