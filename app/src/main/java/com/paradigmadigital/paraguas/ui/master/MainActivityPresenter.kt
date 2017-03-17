package com.paradigmadigital.paraguas.ui.master

import android.util.Log
import com.paradigmadigital.paraguas.domain.Astronomy
import com.paradigmadigital.paraguas.domain.CurrentWeather
import com.paradigmadigital.paraguas.domain.ForecastItem
import javax.inject.Inject

class MainActivityPresenter
@Inject
constructor(
        private val interactor: MainActivityInteractor) {

    val TAG = MainActivityPresenter::class.simpleName

    private var decorator: MainActivityUserInterface? = null

    private val delegate = object : MainActivityUserInterface.Delegate {

        override fun onClick(forecastItem: ForecastItem) = interactor.navigateToDetail(forecastItem)

        override fun onRefresh() = interactor.refresh()
    }

    private val subscriber = object : MainActivityInteractor.RefreshSubscriber {
        override fun handleOnAstronomyResult(astronomy: Astronomy?) {
            Log.d(TAG, astronomy.toString())
            if (astronomy != null) decorator?.showCurrentAstronomy(astronomy)
        }

        override fun handleOnForecastResult(forecast: List<ForecastItem>?) {
            Log.d(TAG, forecast.toString())
            if (forecast != null) decorator?.showForecast(forecast)
        }

        override fun handleOnWheatherResult(currentWeather: CurrentWeather?) {
            Log.d(TAG, currentWeather.toString())
            Log.d(TAG, "City: ${interactor.city}")
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
