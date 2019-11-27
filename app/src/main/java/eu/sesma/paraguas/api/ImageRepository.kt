package eu.sesma.paraguas.api

import androidx.annotation.DrawableRes
import eu.sesma.paraguas.R
import javax.inject.Inject

class ImageRepository
@Inject
constructor() {

    companion object {
        private val ICON_MAP = mapOf(
            "sunny" to R.drawable.sunny,
            "nt_sunny" to R.drawable.nt_sunny,
            "rain" to R.drawable.rain,
            "snow" to R.drawable.snow,
            "sleet" to R.drawable.sleet,
            "wind" to R.drawable.wind,
            "fog" to R.drawable.fog,
            "cloudy" to R.drawable.cloudy,
            "partlycloudy" to R.drawable.partlycloudy,
            "nt_partlycloudy" to R.drawable.nt_partlycloudy,
            "hail" to R.drawable.hail,
            "tstorms" to R.drawable.tstorms
        )
    }

    @DrawableRes
    fun getCurrentIcon(name: String): Int = ICON_MAP[name] ?: R.drawable.sunny
}

