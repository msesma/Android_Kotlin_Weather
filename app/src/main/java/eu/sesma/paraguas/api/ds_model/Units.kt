package eu.sesma.paraguas.api.ds_model

import com.google.gson.annotations.SerializedName

/**
 * Units represents the several types of measurement units that the Forecast API uses
 */
enum class Units private constructor(val text: String) {

    @SerializedName(ModelConstants.UNIT_US)
    US(ModelConstants.UNIT_US),

    @SerializedName(ModelConstants.UNIT_SI)
    SI(ModelConstants.UNIT_SI),

    @SerializedName(ModelConstants.UNIT_CA)
    CA(ModelConstants.UNIT_CA),

    @SerializedName(ModelConstants.UNIT_UK)
    UK(ModelConstants.UNIT_UK),

    @SerializedName(ModelConstants.UNIT_UK2)
    UK2(ModelConstants.UNIT_UK2),

    @SerializedName(ModelConstants.UNIT_AUTO)
    AUTO(ModelConstants.UNIT_AUTO);

    companion object {
        private val map = Icon.values().associateBy(Icon::text)
        fun fromString(text: String) = map[text] ?: AUTO
    }
}
