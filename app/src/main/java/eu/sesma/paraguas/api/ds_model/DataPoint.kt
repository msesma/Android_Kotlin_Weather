package eu.sesma.paraguas.api.ds_model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * A data point object represents the various weather phenomena occurring at a specific instant of time, and
 * has many varied properties. All of these properties (except time) are optional, and will only be set if
 * we have that type of information for that location and time. Please note that minutely data points are
 * always aligned to the nearest minute boundary, hourly points to the top of the hour, and daily points to
 * midnight of that day.
 *
 *
 * Data points in the daily data block [DataBlock] are special: instead of representing the weather
 * phenomena at a given instant of time, they are an aggregate point representing the weather phenomena that
 * will occur over the entire day. For precipitation fields, this aggregate is a maximum; for other fields,
 * it is an average.
 *
 *
 */
class DataPoint {

    @SerializedName(ModelConstants.FIELD_TIME)
    val time: Date? = null

    @SerializedName(ModelConstants.FIELD_SUMMARY)
    val summary: String? = null

    @SerializedName(ModelConstants.FIELD_ICON)
    val icon: Icon? = null

    @SerializedName(ModelConstants.FIELD_SUNSET_TIME)
    val sunsetTime: Date? = null

    @SerializedName(ModelConstants.FIELD_SUNRISE_TIME)
    val sunriseTime: Date? = null

    @SerializedName(ModelConstants.FIELD_MOON_PHASE)
    val moonPhase: Double? = null

    @SerializedName(ModelConstants.FIELD_NEAREST_STORM_DISTANCE)
    val nearestStormDistance: Double? = null

    @SerializedName(ModelConstants.FIELD_NEAREST_STORM_BEARING)
    val nearestStormBearing: Double? = null

    @SerializedName(ModelConstants.FIELD_PRECIP_INTENSITY)
    val precipIntensity: Double? = null

    @SerializedName(ModelConstants.FIELD_PRECIP_INTENSITY_MAX)
    val precipIntensityMax: Double? = null

    @SerializedName(ModelConstants.FIELD_PRECIP_INTENSITY_MAX_TIME)
    val precipIntensityMaxTime: Date? = null

    @SerializedName(ModelConstants.FIELD_PRECIP_PROBABILITY)
    val precipProbability: Double? = null

    @SerializedName(ModelConstants.FIELD_PRECIP_TYPE)
    val precipitationType: PrecipitationType? = null

    @SerializedName(ModelConstants.FIELD_PRECIP_ACCUMULATION)
    val precipAccumulation: Double? = null

    @SerializedName(ModelConstants.FIELD_TEMPERATURE)
    val temperature: Double? = null

    @SerializedName(ModelConstants.FIELD_TEMPERATURE_MIN)
    val temperatureMin: Double? = null

    @SerializedName(ModelConstants.FIELD_TEMPERATURE_MIN_TIME)
    val temperatureMinTime: Date? = null

    @SerializedName(ModelConstants.FIELD_TEMPERATURE_MAX)
    val temperatureMax: Double? = null

    @SerializedName(ModelConstants.FIELD_TEMPERATURE_MAX_TIME)
    val temperatureMaxTime: Date? = null

    @SerializedName(ModelConstants.FIELD_APPARENT_TEMPERATURE)
    val apparentTemperature: Double? = null

    @SerializedName(ModelConstants.FIELD_APPARENT_TEMPERATURE_MIN)
    val apparentTemperatureMin: Double? = null

    @SerializedName(ModelConstants.FIELD_APPARENT_TEMPERATURE_MIN_TIME)
    val apparentTemperatureMinTime: Date? = null

    @SerializedName(ModelConstants.FIELD_APPARENT_TEMPERATURE_MAX)
    val apparentTemperatureMax: Double? = null

    @SerializedName(ModelConstants.FIELD_APPARENT_TEMPERATURE_MAX_TIME)
    val apparentTemperatureMaxTime: Date? = null
    @SerializedName(ModelConstants.FIELD_APPARENT_TEMPERATURE_LOW)
    val apparentTemperatureLow: Double? = null

    @SerializedName(ModelConstants.FIELD_APPARENT_TEMPERATURE_LOW_TIME)
    val apparentTemperatureLowTime: Date? = null

    @SerializedName(ModelConstants.FIELD_APPARENT_TEMPERATURE_HIGH)
    val apparentTemperatureHigh: Double? = null

    @SerializedName(ModelConstants.FIELD_APPARENT_TEMPERATURE_HIGH_TIME)
    val apparentTemperatureHighTime: Date? = null

    @SerializedName(ModelConstants.FIELD_TEMPERATURE_LOW)
    val temperatureLow: Double? = null

    @SerializedName(ModelConstants.FIELD_TEMPERATURE_LOW_TIME)
    val temperatureLowTime: Date? = null

    @SerializedName(ModelConstants.FIELD_TEMPERATURE_HIGH)
    val temperatureHigh: Double? = null

    @SerializedName(ModelConstants.FIELD_TEMPERATURE_HIGH_TIME)
    val temperatureHighTime: Date? = null

    @SerializedName(ModelConstants.FIELD_DEW_POINT)
    val dewPoint: Double? = null

    @SerializedName(ModelConstants.FIELD_WIND_SPEED)
    val windSpeed: Double? = null

    @SerializedName(ModelConstants.FIELD_WIND_BEARING)
    val windBearing: Double? = null

    @SerializedName(ModelConstants.FIELD_CLOUD_COVER)
    val cloudCover: Double? = null

    @SerializedName(ModelConstants.FIELD_HUMIDITY)
    val humidity: Double? = null

    @SerializedName(ModelConstants.FIELD_PRESSURE)
    val pressure: Double? = null

    @SerializedName(ModelConstants.FIELD_VISIBILITY)
    val visibility: Double? = null

    @SerializedName(ModelConstants.FIELD_OZONE)
    val ozone: Double? = null

    @SerializedName(ModelConstants.FIELD_UV_INDEX)
    val uvIndex: Double? = null

    @SerializedName(ModelConstants.FIELD_UV_INDEX_TIME)
    val uvIndexTime: Date? = null
}
