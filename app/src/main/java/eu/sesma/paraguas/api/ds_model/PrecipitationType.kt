package eu.sesma.paraguas.api.ds_model

import com.google.gson.annotations.SerializedName

/**
 * PrecipitationType represents rain, snow, sleet (which applies to each of freezing rain, ice pellets,
 * and “wintry mix”), or hail
 */
enum class PrecipitationType private constructor(val text: String) {

    @SerializedName(ModelConstants.PRECIPITATION_RAIN)
    RAIN(ModelConstants.PRECIPITATION_RAIN),

    @SerializedName(ModelConstants.PRECIPITATION_SNOW)
    SNOW(ModelConstants.PRECIPITATION_SNOW),

    @SerializedName(ModelConstants.PRECIPITATION_SLEET)
    SLEET(ModelConstants.PRECIPITATION_SLEET),

    @SerializedName(ModelConstants.PRECIPITATION_HAIL)
    HAIL(ModelConstants.PRECIPITATION_HAIL);

    companion object {
        private val map = Icon.values().associateBy(Icon::text)
        fun fromString(text: String) = map[text] ?: RAIN
    }
}
