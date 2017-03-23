package com.paradigmadigital.paraguas.ui.master

import android.util.Log
import com.paradigmadigital.paraguas.domain.Astronomy
import com.paradigmadigital.paraguas.domain.City
import com.paradigmadigital.paraguas.domain.CurrentWeather
import com.paradigmadigital.paraguas.domain.ForecastItem
import com.paradigmadigital.paraguas.domain.cache.CacheProvider
import com.paradigmadigital.paraguas.navigation.Navigator
import com.paradigmadigital.paraguas.platform.PermissionManager
import com.paradigmadigital.paraguas.scheduler.ForecastRetriever
import com.paradigmadigital.paraguas.scheduler.Scheduler
import com.paradigmadigital.paraguas.usecases.AstronomyApiUseCase
import com.paradigmadigital.paraguas.usecases.CityUseCase
import com.paradigmadigital.paraguas.usecases.ConditionsApiUseCase
import com.paradigmadigital.paraguas.usecases.ForecastApiUseCase
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
    }

    fun refresh() {
        scheduler.dispatch();

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
