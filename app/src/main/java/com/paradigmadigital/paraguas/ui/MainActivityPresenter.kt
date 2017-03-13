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

        override fun onRefreshButtonClick() {
            interactor.refresh()
        }
    }

    private val subscriber = object : MainActivityInteractor.RefreshSubscriber {
        override fun handleOnAstronomyResult(astronomy: Astronomy?) {
        }

        override fun handleOnHourlyResult(forecast: List<ForecastItem>?) {
        }

        override fun handleOnWheatherResult(currentWeather: CurrentWeather?) {
            decorator?.showMessage("${currentWeather?.temp} ºC")
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

    fun onResume() {
        interactor.refresh()
    }

    fun dispose() {
        this.decorator = null
    }
}
