package com.paradigmadigital.paraguas.domain.cache

import android.util.Log
import com.paradigmadigital.paraguas.api.model.Astronomy
import com.paradigmadigital.paraguas.api.model.CurrentWeather
import com.paradigmadigital.paraguas.api.model.ForecastItem
import com.paradigmadigital.paraguas.domain.City
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class CacheProvider
@Inject constructor() {

    val TAG = CacheProvider::class.simpleName

    val time
        get() = System.currentTimeMillis()


    var city: City? = null
        get() {
            val timedOut = (field?.timestamp ?: 0 < time - TimeUnit.MINUTES.toMillis(5))
            Log.d(TAG, "GET city timed out $timedOut, previous value = $field")
            return if (!timedOut) field else null
        }
        set(value) {
            Log.d(TAG, "SET city $value, previous value = $field")
            if (value != null && !value.city.equals(field?.city)) {
                astronomy = null
                currentWeather = null
                forecast = null
            }
            field = value
        }

    var astronomy: Astronomy? = null
        get() {
            val timedOut = (field?.timestamp ?: 0 < time - TimeUnit.HOURS.toMillis(1))
            Log.d(TAG, "GET astronomy timed out $timedOut, previous value = $field")
            return if (!timedOut) field else null
        }
        set(value) {
            Log.d(TAG, "SET astronomy $value, previous value = $field")
            field = value
        }

    var currentWeather: CurrentWeather? = null
        get() {
            val timedOut = (field?.timestamp ?: 0 < time - TimeUnit.HOURS.toMillis(1))
            Log.d(TAG, "GET currentWeather timed out $timedOut, previous value = $field")
            return if (!timedOut) field else null
        }
        set(value) {
            Log.d(TAG, "SET currentWeather $value, previous value = $field")
            field = value
        }

    var forecast: List<ForecastItem>? = null
        get() {
            val timedOut = (field?.get(0)?.timestamp ?: 0 < time - TimeUnit.HOURS.toMillis(1))
            Log.d(TAG, "GET forecast timed out $timedOut, previous value = $field")
            return if (!timedOut) field else null
        }
        set(value) {
            Log.d(TAG, "SET forecast $value, previous value = $field")
            field = value
        }
}


