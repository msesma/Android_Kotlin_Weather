package com.paradigmadigital.paradigma.splash

import com.paradigmadigital.paradigma.api.model.Weather
import com.paradigmadigital.paradigma.api.model.WeatherData
import com.paradigmadigital.paradigma.usecases.CurrentWeatherUseCase
import javax.inject.Inject

class SplashActivityInteractor
@Inject
constructor(private val useCase: CurrentWeatherUseCase) {

    fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)

    private var subscriber: RefreshSubscriber? = null

    fun initialize(subscriber: RefreshSubscriber) {
        this.subscriber = subscriber
    }

    fun refresh(city: String) {
        useCase.execute(city).subscribe({ this.handleOnResult(it) }, { this.handleOnError(it) })
    }

    private fun handleOnResult(weatherData: WeatherData?) {
        val temp: Double =  (weatherData?.main?.temp ?: 0.0) - 273.15
        val text = weatherData?.weather?.get(0)?.description + ", " + temp.format(1)  + "ºC"

        subscriber?.onResult(text)
    }

    private fun handleOnError(throwable: Throwable) {
        subscriber?.onError(throwable.cause as Exception)
    }

    interface RefreshSubscriber {
        fun onError(ex: Exception)

        fun onResult(currentWeather: String)
    }

    fun  getCity(): String {
        return "Madrid"
    }
}
