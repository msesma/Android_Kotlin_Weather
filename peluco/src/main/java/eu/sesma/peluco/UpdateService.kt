package eu.sesma.peluco

import android.preference.PreferenceManager
import android.util.Log
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.WearableListenerService
import org.json.JSONArray

class UpdateService : WearableListenerService() {
    override fun onDataChanged(dataEvents: DataEventBuffer?) {
        dataEvents?.forEach {
            if (it.type == DataEvent.TYPE_CHANGED) {
                with(DataMapItem.fromDataItem(it.dataItem).dataMap) {
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
    }

    private fun persistWeatherData(
        temps: List<Int>,
        rainsQpf: List<Int>,
        rainsPop: List<Int>,
        sunrise: Long,
        sunset: Long,
        city: String,
        icon: String
    ) {
        val settings = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = settings.edit()

        val tempsJSONArray = JSONArray(temps)
        val rainsQpfJSONArray = JSONArray(rainsQpf)
        val rainsPopJSONArray = JSONArray(rainsPop)
//        Log.d("===>", "${tempsJSONArray}")
//        Log.d("===>", "${rainsQpfJSONArray}")
//        Log.d("===>", "${rainsPopJSONArray}")

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