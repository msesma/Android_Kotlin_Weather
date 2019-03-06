package eu.sesma.paraguas.usecases

import eu.sesma.paraguas.api.Constants
import eu.sesma.paraguas.api.ds_model.Forecast
import eu.sesma.paraguas.api.ds_model.Language
import eu.sesma.paraguas.api.ds_model.Units
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
    private val service: WeatherService,
    private val mapper: ForecastMapper,
    private val cacheControl: CacheControl
) {
    fun execute(city: City) = getForecast(
        latitude = city.location.latitude,
        longitude = city.location.longitude
    )
        .map { mapper.map(Pair(it, city)) }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


    private fun getForecast(
        latitude: Double, longitude: Double
    ): Single<Forecast> {
        return getForecast(latitude, longitude, null)
    }

    private fun getForecast(
        latitude: Double, longitude: Double, time: Int?
    ): Single<Forecast> {
        return getForecast(latitude, longitude, time, null, null, null, false)
    }

    private fun getForecast(
        latitude: Double, longitude: Double, time: Int?,
        language: Language?, units: Units?,
        excludeList: List<String>?, extendHourly: Boolean
    ): Single<Forecast> {

        return service.getForecast(
            latitude.toString(), longitude.toString(),
            getQueryMapParameters(
                language, units, excludeList,
                extendHourly
            ),
            cacheControl.toString()
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