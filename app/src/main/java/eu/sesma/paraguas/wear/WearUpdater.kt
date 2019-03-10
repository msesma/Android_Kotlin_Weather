package eu.sesma.paraguas.wear

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable
import eu.sesma.paraguas.domain.Astronomy
import eu.sesma.paraguas.domain.CurrentWeather
import eu.sesma.paraguas.domain.ForecastItem
import eu.sesma.paraguas.log.DiskLogger
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WearUpdater
@Inject constructor(
    private val context: Context,
    private val diskLogger: DiskLogger
) {

    private val TAG = WearUpdater::class.simpleName

    private var googleApiClient: GoogleApiClient? = null
    lateinit private var currentWeather: CurrentWeather
    lateinit private var astronomy: Astronomy
    lateinit private var forecast: List<ForecastItem>
    lateinit private var city: String

    private val connectionCallback = object : GoogleApiClient.ConnectionCallbacks {
        override fun onConnected(bundle: Bundle?) {
            handleOnConnected()
        }

        override fun onConnectionSuspended(i: Int) {
        }
    }

    fun update(currentWeather: CurrentWeather, astronomy: Astronomy, forecast: List<ForecastItem>, city: String) {
        this.currentWeather = currentWeather
        this.astronomy = astronomy
        this.forecast = forecast
        this.city = city

        buildGoogleApiClient()
    }

    private fun handleOnConnected() {

        val sunrise = astronomy.sunrise?.time
        val sunset = astronomy.sunset?.time
        val temps = mutableListOf<Int>()
        val rainsQpf = mutableListOf<Int>()
        val rainsPop = mutableListOf<Int>()

        if (forecast[0].time?.hours != Date().hours) {
            temps.add((currentWeather.temp * 10).toInt())
            rainsQpf.add(currentWeather.precip1hrMetric.toInt())
            rainsPop.add(0)
        }
        forecast.forEach {
            temps.add((it.temp * 10).toInt())
            rainsQpf.add((it.rainQuantity * 100).toInt())
            rainsPop.add((it.rainProbability * 100).toInt())
        }
        rainsPop[0] = if (rainsQpf[0] > 0) 50 else rainsPop[1]

        val putDataMapRequest = PutDataMapRequest.create(WearConstants.WATCH_SET_FORECAST_PATH)
        putDataMapRequest.dataMap.putIntegerArrayList(WearConstants.KEY_TEMPS, temps as ArrayList<Int>)
        putDataMapRequest.dataMap.putIntegerArrayList(WearConstants.KEY_RAINS_QPF, rainsQpf as ArrayList<Int>)
        putDataMapRequest.dataMap.putIntegerArrayList(WearConstants.KEY_RAINS_POP, rainsPop as ArrayList<Int>)
        putDataMapRequest.dataMap.putLong(WearConstants.KEY_SUNRISE, sunrise ?: 0)
        putDataMapRequest.dataMap.putLong(WearConstants.KEY_SUNSET, sunset ?: TimeUnit.DAYS.toMillis(1))
        putDataMapRequest.dataMap.putString(WearConstants.CITY, city)
        putDataMapRequest.dataMap.putString(WearConstants.ICON, currentWeather.iconName)

        putDataMapRequest.dataMap.putLong(WearConstants.LAST_UPDATE_TIME, Date().time)
        val request = putDataMapRequest.asPutDataRequest()
        Wearable.DataApi.putDataItem(googleApiClient, request)
            .setResultCallback { dataItemResult ->
                val status = dataItemResult.status.isSuccess
                if (!status) {
                    diskLogger.log(TAG, "Update Wearable forecast(): Failed to set the data, status: $status")
                } else {
                    Log.d(TAG, "Update Wearable forecast(): Success status: $status")
                }
            }
    }

    @Synchronized
    private fun buildGoogleApiClient() {
        if (googleApiClient == null) {
            googleApiClient = GoogleApiClient.Builder(context)
                .addConnectionCallbacks(connectionCallback)
                .addApi(Wearable.API)
                .build()
        }

        googleApiClient?.connect()
    }
}
