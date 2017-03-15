package com.paradigmadigital.paraguas.ui

import android.util.Log
import com.paradigmadigital.paraguas.api.model.Astronomy
import com.paradigmadigital.paraguas.api.model.CurrentWeather
import com.paradigmadigital.paraguas.api.model.ForecastItem
import com.paradigmadigital.paraguas.domain.City
import com.paradigmadigital.paraguas.domain.cache.CacheProvider
import com.paradigmadigital.paraguas.platform.PermissionManager
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
        private val permissionManager: PermissionManager) {

    val TAG = MainActivityInteractor::class.simpleName

    private var subscriber: RefreshSubscriber? = null

    val city get() = cache.city?.city

    fun initialize(subscriber: RefreshSubscriber) {
        this.subscriber = subscriber
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
                            {cache.city = it; this.handleOnCityResult(it) },
                            { subscriber?.onError(it.cause as Exception) }
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

    interface RefreshSubscriber {
        fun onError(ex: Exception)

        fun handleOnWheatherResult(currentWeather: CurrentWeather?)

        fun handleOnAstronomyResult(astronomy: Astronomy?)

        fun handleOnForecastResult(forecast: List<ForecastItem>?)
    }
}
