package com.paradigmadigital.peluco

import android.text.format.Time
import java.util.*
import java.util.concurrent.TimeUnit

class IconMapper {
    companion object {
        private val ICON_MAP = HashMap<String, Int>()
    }

    init {
        ICON_MAP.put("chanceflurries", R.drawable.chanceflurries)
        ICON_MAP.put("nt_chanceflurries", R.drawable.nt_chanceflurries)

        ICON_MAP.put("chancerain", R.drawable.chancerain)
        ICON_MAP.put("nt_chancerain", R.drawable.nt_chancerain)

        ICON_MAP.put("chancesleet", R.drawable.chancesleet)
        ICON_MAP.put("nt_chancesleet", R.drawable.nt_chancesleet)

        ICON_MAP.put("chancesnow", R.drawable.chancesnow)
        ICON_MAP.put("nt_chancesnow", R.drawable.nt_chancesnow)

        ICON_MAP.put("chancetstorms", R.drawable.chancetstorms)
        ICON_MAP.put("nt_chancetstorms", R.drawable.nt_chancetstorms)

        ICON_MAP.put("clear", R.drawable.clear)
        ICON_MAP.put("nt_clear", R.drawable.nt_clear)

        ICON_MAP.put("cloudy", R.drawable.cloudy)
        ICON_MAP.put("nt_cloudy", R.drawable.nt_cloudy)

        ICON_MAP.put("flurries", R.drawable.flurries)
        ICON_MAP.put("nt_flurries", R.drawable.nt_flurries)

        ICON_MAP.put("fog", R.drawable.fog)
        ICON_MAP.put("nt_fog", R.drawable.nt_fog)

        ICON_MAP.put("hazy", R.drawable.hazy)
        ICON_MAP.put("nt_hazy", R.drawable.nt_hazy)

        ICON_MAP.put("mostlycloudy", R.drawable.mostlycloudy)
        ICON_MAP.put("nt_mostlycloudy", R.drawable.nt_mostlycloudy)

        ICON_MAP.put("mostlysunny", R.drawable.mostlysunny)
        ICON_MAP.put("nt_mostlysunny", R.drawable.nt_mostlysunny)

        ICON_MAP.put("partlycloudy", R.drawable.partlycloudy)
        ICON_MAP.put("nt_partlycloudy", R.drawable.nt_partlycloudy)

        ICON_MAP.put("partlysunny", R.drawable.partlysunny)
        ICON_MAP.put("nt_partlysunny", R.drawable.nt_partlysunny)

        ICON_MAP.put("rain", R.drawable.rain)
        ICON_MAP.put("nt_rain", R.drawable.nt_rain)

        ICON_MAP.put("sleet", R.drawable.sleet)
        ICON_MAP.put("nt_sleet", R.drawable.nt_sleet)

        ICON_MAP.put("snow", R.drawable.snow)
        ICON_MAP.put("nt_snow", R.drawable.nt_snow)

        ICON_MAP.put("sunny", R.drawable.sunny)
        ICON_MAP.put("nt_sunny", R.drawable.nt_sunny)

        ICON_MAP.put("tstorms", R.drawable.tstorms)
        ICON_MAP.put("nt_tstorms", R.drawable.nt_tstorms)
    }

    fun map(key: String, sunrise: Long, sunset: Long): Int {
        if (isDayLight(sunrise, sunset)) {
            return ICON_MAP[key] ?: R.drawable.clear
        }
        return ICON_MAP["nt_" + key] ?: R.drawable.nt_clear
    }

    private fun isDayLight(sunrise: Long, sunset: Long): Boolean {
        val time = Time()
        time.setToNow()
        val now = TimeUnit.HOURS.toMillis(time.hour.toLong()) + TimeUnit.MINUTES.toMillis(time.minute.toLong())
        return sunrise < now && now < sunset
    }

}
