package com.paradigmadigital.paradigma.ui

import com.paradigmadigital.paradigma.api.model.WuConditionsData
import com.paradigmadigital.paradigma.usecases.ConditionsUseCase
import javax.inject.Inject

class MainActivityInteractor
@Inject
constructor(private val useCase: ConditionsUseCase) {

    fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)

    private var subscriber: RefreshSubscriber? = null

    fun initialize(subscriber: RefreshSubscriber) {
        this.subscriber = subscriber
    }

    fun refresh(city: String) {
        useCase.execute(city = city).subscribe({ this.handleOnResult(it) }, { this.handleOnError(it) })
    }

    private fun handleOnResult(weatherData: WuConditionsData?) {
        val temp: Float = weatherData?.currenObservation?.tempC ?: 0f
        val text = weatherData?.currenObservation?.icon + ", " + temp + "ºC"

        subscriber?.onResult(text)
    }

    private fun handleOnError(throwable: Throwable) {
        subscriber?.onError(throwable.cause as Exception)
    }

    interface RefreshSubscriber {
        fun onError(ex: Exception)

        fun onResult(currentWeather: String)
    }

    fun getCity(): String {
        return "Madrid"
    }
}
