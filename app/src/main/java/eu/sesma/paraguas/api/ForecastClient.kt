package eu.sesma.paraguas.api

import eu.sesma.paraguas.api.ds_model.Forecast
import eu.sesma.paraguas.api.ds_model.Language
import eu.sesma.paraguas.api.ds_model.Units
import eu.sesma.paraguas.api.services.WeatherService
import io.reactivex.Single
import okhttp3.CacheControl
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastClient
@Inject private constructor(
    private val mService: WeatherService,
    private val mCacheControl: CacheControl
) {
    /**
     * Asynchronous Forecast call
     *
     * @param latitude         latitude of the location
     * @param longitude        longitude of the location
     * @param forecastCallback [Callback] to be invoked when request completes, with a [Forecast]
     * @return [Call] object, so that you may cancel the request
     */
    fun getForecast(
        latitude: Double, longitude: Double
    ): Single<Forecast> {
        return getForecast(latitude, longitude, null)
    }

    /**
     * Asynchronous Forecast call
     *
     * @param latitude         latitude of the location
     * @param longitude        longitude of the location
     * @param time             Nullable (optional) time to add to the request
     */
    fun getForecast(
        latitude: Double, longitude: Double, time: Int?
    ): Single<Forecast> {
        return getForecast(latitude, longitude, time, null, null, null, false)
    }

    /**
     * Asynchronous Forecast call
     *
     * @param latitude         latitude of the location
     * @param longitude        longitude of the location
     * @param time             Nullable (optional) time to add to the request
     * @param language         Nullable [Language], if null, will be the default from the [ForecastConfiguration]
     * @param units             Nullable [Units], if null, will be the default from the [ForecastConfiguration]
     * @param excludeList      Nullable List of blocks to exclude, if null, will be the default from the
     * [ForecastConfiguration]. Values may include
     * [Constants.OPTIONS_EXCLUDE_CURRENLY],
     * [Constants.OPTIONS_EXCLUDE_MINUTELY],
     * [Constants.OPTIONS_EXCLUDE_HOURLY]
     * [Constants.OPTIONS_EXCLUDE_DAILY],
     * [Constants.OPTIONS_EXCLUDE_ALERTS],
     * [Constants.OPTIONS_EXCLUDE_FLAGS]
     * @param extendHourly     if true, will extend the hourly DataBlock with data for 7 days, rather than the default of 2
     */
    fun getForecast(
        latitude: Double, longitude: Double, time: Int?,
        language: Language?, units: Units?,
        excludeList: List<String>?, extendHourly: Boolean
    ): Single<Forecast> {

        return mService.getForecast(
            latitude.toString(), longitude.toString(),
            getQueryMapParameters(
                language, units, excludeList,
                extendHourly
            ),
            mCacheControl.toString()
        )
    }

    private fun getQueryMapParameters(
        language: Language?,
        units: Units?,
        excludeBlocks: List<String>?,
        extendHourly: Boolean
    ): Map<String, String> {
        val queryMap = mutableMapOf<String, String>()

        queryMap[Constants.OPTIONS_LANGUAGE] = language?.text ?: Language.ENGLISH.text
        queryMap[Constants.OPTIONS_UNIT] = units?.text ?: Units.SI.text
        if (excludeBlocks != null) queryMap[Constants.OPTIONS_EXCLUDE] = excludeBlocks.joinToString { "," }
        if (extendHourly) queryMap[Constants.OPTIONS_EXTEND] = Constants.OPTIONS_EXTEND_HOURLY

        return queryMap.toMap()
    }
}

