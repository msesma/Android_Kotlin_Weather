package com.paradigmadigital.paraguas.ui

import com.paradigmadigital.paraguas.api.model.CurrentWeather
import com.paradigmadigital.paraguas.domain.City
import com.paradigmadigital.paraguas.platform.PermissionManager
import com.paradigmadigital.paraguas.usecases.CityUseCase
import com.paradigmadigital.paraguas.usecases.ConditionsApiUseCase
import javax.inject.Inject

class MainActivityInteractor
@Inject
constructor(
        private val conditionsUseCase: ConditionsApiUseCase,
        //        private val astronomyUseCase: AstronomyApiUseCase,
//        private val hourlyUseCase: ForecastApiUseCase,
        private val cityUseCase: CityUseCase,
        private val permissionManager: PermissionManager) {

    private var subscriber: RefreshSubscriber? = null

    var city: String? = null
        private set

    fun initialize(subscriber: RefreshSubscriber) {
        this.subscriber = subscriber
    }

    fun refresh() {
        if (hasLocationPermission()) {
            cityUseCase.execute()
                    .subscribe({ this.handleOnCityResult(it) }, { subscriber?.onError(it.cause as Exception) })
        }
    }

    private fun handleOnCityResult(city: City) {
        this.city = city.city
        conditionsUseCase.execute(country = city.countryCode, city = city.city)
                .subscribe({ subscriber?.handleOnWheatherResult(it) }, { subscriber?.onError(it.cause as Exception) })
//        conditionsUseCase.execute(country = city.countryCode, city = city.city)
//                .subscribe({ subscriber?.handleOnAstronomyResult(it) }, { subscriber?.onError(it.cause as Exception) })
//        conditionsUseCase.execute(country = city.countryCode, city = city.city)
//                .subscribe({ subscriber?.handleOnHourlyResult(it) }, { subscriber?.onError(it.cause as Exception) })
    }

    interface RefreshSubscriber {
        fun onError(ex: Exception)

        fun handleOnWheatherResult(currentWeather: CurrentWeather?)
//        fun handleOnAstronomyResult(weatherData: ConditionsData?)

//        fun handleOnHourlyResult(weatherData: ConditionsData?)
    }

    private fun hasLocationPermission(): Boolean {
        return permissionManager.locationPremission;
    }

}
