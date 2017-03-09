package com.paradigmadigital.paraguas.ui

import com.paradigmadigital.paraguas.api.model.ConditionsData
import com.paradigmadigital.paraguas.domain.City
import com.paradigmadigital.paraguas.platform.PermissionManager
import com.paradigmadigital.paraguas.usecases.CityUseCase
import com.paradigmadigital.paraguas.usecases.ConditionsApiUseCase
import javax.inject.Inject

class MainActivityInteractor
@Inject
constructor(
        private val conditionsUseCase: ConditionsApiUseCase,
        private val cityUseCase: CityUseCase,
        private val permissionManager: PermissionManager) {

    private var subscriber: RefreshSubscriber? = null

    private var city: String? = null

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
                .subscribe({ this.handleOnWheatherResult(it) }, { subscriber?.onError(it.cause as Exception) })
    }

    private fun handleOnWheatherResult(weatherData: ConditionsData?) {
        val temp: Float = weatherData?.currenObservation?.tempC ?: 0f
        val text = weatherData?.currenObservation?.icon + ", " + temp + "ºC"

        subscriber?.onResult(text, city ?: "City not found")
        city = null;
    }

    interface RefreshSubscriber {
        fun onError(ex: Exception)

        fun onResult(currentWeather: String, city: String)
    }

    private fun hasLocationPermission(): Boolean {
        return permissionManager.locationPremission;
    }
}
