package eu.sesma.paraguas.ui.master

import android.util.Log
import eu.sesma.paraguas.domain.Astronomy
import eu.sesma.paraguas.domain.City
import eu.sesma.paraguas.domain.CurrentWeather
import eu.sesma.paraguas.domain.ForecastItem
import eu.sesma.paraguas.domain.cache.CacheProvider
import eu.sesma.paraguas.navigation.Navigator
import eu.sesma.paraguas.platform.PermissionManager
import eu.sesma.paraguas.scheduler.ForecastRetriever
import eu.sesma.paraguas.scheduler.Scheduler
import eu.sesma.paraguas.usecases.AstronomyApiUseCase
import eu.sesma.paraguas.usecases.CityUseCase
import eu.sesma.paraguas.usecases.ConditionsApiUseCase
import eu.sesma.paraguas.usecases.ForecastApiUseCase
import javax.inject.Inject

class MainActivityInteractor
@Inject
constructor(
        private val conditionsUseCase: ConditionsApiUseCase,
        private val astronomyUseCase: AstronomyApiUseCase,
        private val hourlyUseCase: ForecastApiUseCase,
        private val cityUseCase: CityUseCase,
        private val cache: CacheProvider,
        private val permissionManager: PermissionManager,
        private val navigator: Navigator,
        private val scheduler: Scheduler
) {

    val TAG = MainActivityInteractor::class.simpleName

    private var subscriber: RefreshSubscriber? = null

    val city get() = cache.city?.city

    fun initialize(subscriber: RefreshSubscriber) {
        this.subscriber = subscriber
        scheduler.start()
    }

    fun refresh() {

        val city = cache.city
        if (city != null) {
            handleOnCityResult(city)
            return
        }

        if (permissionManager.locationPremission) {
            cityUseCase.execute()
                    .subscribe(
                            { cache.city = it; this.handleOnCityResult(it) },
                            { subscriber?.onError(it as Exception) }
                    )
        }
    }

    private fun handleOnCityResult(city: City) {
        Log.d(TAG, cache.city.toString())
        if (cache.currentWeather == null) conditionsUseCase.execute(country = city.countryCode, city = city.city)
                .subscribe(
                        { cache.currentWeather = it; subscriber?.handleOnWheatherResult(it) },
                        { subscriber?.onError(it.cause as Exception) }
                )
        else subscriber?.handleOnWheatherResult(cache.currentWeather)

        if (cache.astronomy == null) astronomyUseCase.execute(country = city.countryCode, city = city.city)
                .subscribe(
                        { cache.astronomy = it; subscriber?.handleOnAstronomyResult(it) },
                        { subscriber?.onError(it.cause as Exception) }
                )
        else subscriber?.handleOnAstronomyResult(cache.astronomy)

        if (cache.forecast == null) hourlyUseCase.execute(country = city.countryCode, city = city.city)
                .subscribe(
                        { cache.forecast = it; subscriber?.handleOnForecastResult(it) },
                        { subscriber?.onError(it.cause as Exception) }
                )
        else subscriber?.handleOnForecastResult(cache.forecast)
    }

    fun navigateToDetail(forecastItem: ForecastItem) {
        navigator.navigateToDetail(forecastItem)
    }

    interface RefreshSubscriber {
        fun onError(ex: Exception)

        fun handleOnWheatherResult(currentWeather: CurrentWeather?)

        fun handleOnAstronomyResult(astronomy: Astronomy?)

        fun handleOnForecastResult(forecast: List<ForecastItem>?)
    }

}
