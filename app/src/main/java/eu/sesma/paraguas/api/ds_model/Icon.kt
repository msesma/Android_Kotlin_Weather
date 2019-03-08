package eu.sesma.paraguas.api.ds_model

import com.google.gson.annotations.SerializedName

/**
 * Icons for types of weather
 */
enum class Icon private constructor(val text: String) {

    @SerializedName(ModelConstants.ICON_CLEAR_DAY)
    CLEAR_DAY("sunny"),

    @SerializedName(ModelConstants.ICON_CLEAR_NIGHT)
    CLEAR_NIGHT("nt_sunny"),

    @SerializedName(ModelConstants.ICON_RAIN)
    RAIN("rain"),

    @SerializedName(ModelConstants.ICON_SNOW)
    SNOW("snow"),

    @SerializedName(ModelConstants.ICON_SLEET)
    SLEET("sleet"),

    @SerializedName(ModelConstants.ICON_WIND)
    WIND("wind"),

    @SerializedName(ModelConstants.ICON_FOG)
    FOG("fog"),

    @SerializedName(ModelConstants.ICON_CLOUDY)
    CLOUDY("cloudy"),

    @SerializedName(ModelConstants.ICON_PARTLY_CLOUDY_DAY)
    PARTLY_CLOUDY_DAY("partlucloudy"),

    @SerializedName(ModelConstants.ICON_PARTLY_CLOUDY_NIGHT)
    PARTLY_CLOUDY_NIGHT("nt_partlycloudy"),

    @SerializedName(ModelConstants.ICON_HAIL)
    HAIL("hail"),

    @SerializedName("tstorms")
    THUNDERSTORM(ModelConstants.ICON_THUNDERSTORM),

    @SerializedName(ModelConstants.ICON_TORNADO)
    TORNADO("wind");

    companion object {
        private val map = Icon.values().associateBy(Icon::text)
        fun fromString(text: String) = map[text] ?: CLEAR_DAY
    }
}
