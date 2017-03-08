package com.paradigmadigital.paradigma.ui

import com.paradigmadigital.paradigma.api.model.ConditionsData
import com.paradigmadigital.paradigma.domain.City
import com.paradigmadigital.paradigma.usecases.CityUseCase
import com.paradigmadigital.paradigma.usecases.ConditionsApiUseCase
import javax.inject.Inject

class MainActivityInteractor
@Inject
constructor(private val conditionsUseCase: ConditionsApiUseCase, private val cityUseCase: CityUseCase) {

    private var subscriber: RefreshSubscriber? = null

    private var city: String? = null

    fun initialize(subscriber: RefreshSubscriber) {
        this.subscriber = subscriber
    }

    fun refresh() {
        cityUseCase.execute()
                .subscribe({ this.handleOnCityResult(it) }, { subscriber?.onError(it.cause as Exception) })

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
}
