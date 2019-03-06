package eu.sesma.paraguas.api.ds_model

import com.google.gson.annotations.SerializedName

/**
 * Icons for types of weather
 */
enum class Icon private constructor(val text: String) {

    @SerializedName(ModelConstants.ICON_CLEAR_DAY)
    CLEAR_DAY(ModelConstants.ICON_CLEAR_DAY),

    @SerializedName(ModelConstants.ICON_CLEAR_NIGHT)
    CLEAR_NIGHT(ModelConstants.ICON_CLEAR_NIGHT),

    @SerializedName(ModelConstants.ICON_RAIN)
    RAIN(ModelConstants.ICON_RAIN),

    @SerializedName(ModelConstants.ICON_SNOW)
    SNOW(ModelConstants.ICON_SNOW),

    @SerializedName(ModelConstants.ICON_SLEET)
    SLEET(ModelConstants.ICON_SLEET),

    @SerializedName(ModelConstants.ICON_WIND)
    WIND(ModelConstants.ICON_WIND),

    @SerializedName(ModelConstants.ICON_FOG)
    FOG(ModelConstants.ICON_FOG),

    @SerializedName(ModelConstants.ICON_CLOUDY)
    CLOUDY(ModelConstants.ICON_CLOUDY),

    @SerializedName(ModelConstants.ICON_PARTLY_CLOUDY_DAY)
    PARTLY_CLOUDY_DAY(ModelConstants.ICON_PARTLY_CLOUDY_DAY),

    @SerializedName(ModelConstants.ICON_PARTLY_CLOUDY_NIGHT)
    PARTLY_CLOUDY_NIGHT(ModelConstants.ICON_PARTLY_CLOUDY_NIGHT),

    @SerializedName(ModelConstants.ICON_HAIL)
    HAIL(ModelConstants.ICON_HAIL),

    @SerializedName(ModelConstants.ICON_THUNDERSTORM)
    THUNDERSTORM(ModelConstants.ICON_THUNDERSTORM),

    @SerializedName(ModelConstants.ICON_TORNADO)
    TORNADO(ModelConstants.ICON_TORNADO);

    companion object {
        private val map = Icon.values().associateBy(Icon::text)
        fun fromString(text: String) = map[text] ?: CLEAR_DAY
    }
}
