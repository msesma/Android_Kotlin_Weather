package eu.sesma.paraguas.domain.cache

import android.util.Log
import eu.sesma.paraguas.domain.City
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class CacheProvider
@Inject constructor() {

    val TAG = CacheProvider::class.simpleName

    val time get() = System.currentTimeMillis()

    var cityTimestamp = 0L
    var city: City? = null
        get() {
            val timedOut = (cityTimestamp < time - TimeUnit.MINUTES.toMillis(5))
            Log.d(TAG, "GET city timed out $timedOut, previous value = $field")
            return if (!timedOut) field else null
        }
        set(value) {
            Log.d(TAG, "SET city $value, previous value = $field")
            cityTimestamp = time
            field = value
        }
}


