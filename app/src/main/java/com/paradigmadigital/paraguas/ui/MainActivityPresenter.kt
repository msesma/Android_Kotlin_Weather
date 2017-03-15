package com.paradigmadigital.paraguas.ui

import com.paradigmadigital.paraguas.api.model.Astronomy
import com.paradigmadigital.paraguas.api.model.CurrentWeather
import com.paradigmadigital.paraguas.api.model.ForecastItem
import javax.inject.Inject

class MainActivityPresenter
@Inject
constructor(
        private val interactor: MainActivityInteractor) {

    private var decorator: MainActivityUserInterface? = null

    private val delegate = object : MainActivityUserInterface.Delegate {

        override fun onRefresh() {
            interactor.refresh()
        }
    }

    private val subscriber = object : MainActivityInteractor.RefreshSubscriber {
        override fun handleOnAstronomyResult(astronomy: Astronomy?) {
            if (astronomy != null) decorator?.showCurrentAstronomy(astronomy)
        }

        override fun handleOnHourlyResult(forecast: List<ForecastItem>?) {
            if (forecast != null) decorator?.showForecast(forecast)
        }

        override fun handleOnWheatherResult(currentWeather: CurrentWeather?) {
            if (currentWeather != null) decorator?.showCurrentWeather(currentWeather)
            decorator?.setCity(interactor.city ?: "")
        }

        override fun onError(ex: Exception) {
            decorator?.showError(ex.message ?: ex.toString())
        }
    }

    fun initialize(decorator: MainActivityUserInterface) {
        interactor.initialize(subscriber)
        this.decorator = decorator
        this.decorator?.initialize(delegate)
    }

    fun onResume() = interactor.refresh()

    fun dispose() {
        this.decorator = null
    }
}
