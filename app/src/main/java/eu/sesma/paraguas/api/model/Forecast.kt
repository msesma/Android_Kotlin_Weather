package eu.sesma.paraguas.api.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * In general, to determine the weather at a given point in time, one should examine the highest-precision
 * data block defined (minutely, hourly, and daily respectively), taking any data available from from it
 * and falling back to the next-highest precision data block for any properties that are missing for the
 * point in time desired.
 */
class Forecast {

    @SerializedName(ModelConstants.FIELD_LATITUDE)
    val latitude: Double = 0.toDouble()

    @SerializedName(ModelConstants.FIELD_LONGITUDE)
    val longitude: Double = 0.toDouble()

    @SerializedName(ModelConstants.FIELD_TIMEZONE)
    val timezone: String? = null

    @SerializedName(ModelConstants.FIELD_OFFSET)
    val offset: Double = 0.toDouble()

    @SerializedName(ModelConstants.FIELD_CURRENTLY)
    val currently: DataPoint? = null

    @SerializedName(ModelConstants.FIELD_MINUTELY)
    val minutely: DataBlock? = null

    @SerializedName(ModelConstants.FIELD_HOURLY)
    val hourly: DataBlock? = null

    @SerializedName(ModelConstants.FIELD_DAILY)
    val daily: DataBlock? = null

    @SerializedName(ModelConstants.FIELD_ALERTS)
    val alerts: ArrayList<Alert>? = null

    @SerializedName(ModelConstants.FIELD_FLAGS)
    val flags: Flags? = null
}
