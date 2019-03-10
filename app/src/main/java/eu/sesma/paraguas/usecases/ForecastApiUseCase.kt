package eu.sesma.paraguas.usecases

import android.content.Context
import eu.sesma.paraguas.R
import eu.sesma.paraguas.api.Constants
import eu.sesma.paraguas.api.model.Forecast
import eu.sesma.paraguas.api.model.Language
import eu.sesma.paraguas.api.model.Units
import eu.sesma.paraguas.api.services.WeatherService
import eu.sesma.paraguas.domain.City
import eu.sesma.paraguas.domain.mappers.ForecastMapper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.CacheControl
import javax.inject.Inject

//TODO set exact parameters of the query
class ForecastApiUseCase
@Inject constructor(
    context: Context,
    private val service: WeatherService,
    private val mapper: ForecastMapper,
    private val cacheControl: CacheControl
) {
    private val key = context.getString(R.string.DarkSky_api_token) ?: ""

    fun execute(city: City) = getForecast(city.location.latitude, city.location.longitude)
        .map { mapper.map(Pair(it, city)) }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    private fun getForecast(latitude: Double, longitude: Double): Single<Forecast> =
        getForecast(latitude, longitude, null, null, null, false)

    private fun getForecast(
        latitude: Double,
        longitude: Double,
        language: Language?,
        units: Units?,
        excludeList: List<String>?,
        extendHourly: Boolean
    ): Single<Forecast> = service.getForecast(
        key,
        latitude.toString(), longitude.toString(),
        getQueryMapParameters(language, units, excludeList, extendHourly),
        cacheControl.toString()
    )

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