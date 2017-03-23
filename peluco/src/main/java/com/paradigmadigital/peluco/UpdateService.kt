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

package com.paradigmadigital.peluco

import android.preference.PreferenceManager
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.WearableListenerService
import org.json.JSONArray

class UpdateService : WearableListenerService() {
    override fun onDataChanged(dataEvents: DataEventBuffer?) {
        for (dataEvent in dataEvents!!) {
            if (dataEvent.type != DataEvent.TYPE_CHANGED) continue

            with(DataMapItem.fromDataItem(dataEvent.dataItem).dataMap) {
                val temps = getIntegerArrayList(WearConstants.KEY_TEMPS)
                val rainsQpf = getIntegerArrayList(WearConstants.KEY_RAINS_QPF)
                val rainsPop = getIntegerArrayList(WearConstants.KEY_RAINS_POP)
                val sunrise = getLong(WearConstants.KEY_SUNRISE)
                val sunset = getLong(WearConstants.KEY_SUNSET)
                val city = getString(WearConstants.CITY)
                val icon = getString(WearConstants.ICON)
                persistWeatherData(temps, rainsQpf, rainsPop, sunrise, sunset, city, icon)
            }

        }
    }

    private fun persistWeatherData(
            temps: List<Int>,
            rainsQpf: List<Int>,
            rainsPop: List<Int>,
            sunrise: Long,
            sunset: Long,
            city: String,
            icon: String) {
        val settings = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = settings.edit()

        val tempsJSONArray = JSONArray(temps)
        val rainsQpfJSONArray = JSONArray(rainsQpf)
        val rainsPopJSONArray = JSONArray(rainsPop)

        editor.putString(WearConstants.KEY_TEMPS, tempsJSONArray.toString())
        editor.putString(WearConstants.KEY_RAINS_QPF, rainsQpfJSONArray.toString())
        editor.putString(WearConstants.KEY_RAINS_POP, rainsPopJSONArray.toString())
        editor.putLong(WearConstants.KEY_SUNRISE, sunrise)
        editor.putLong(WearConstants.KEY_SUNSET, sunset)
        editor.putString(WearConstants.CITY, city)
        editor.putString(WearConstants.ICON, icon)
        editor.putLong(WearConstants.LAST_UPDATE_TIME, System.currentTimeMillis())

        editor.apply()
    }
}