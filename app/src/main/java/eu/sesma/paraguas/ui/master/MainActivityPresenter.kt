package eu.sesma.paraguas.ui.master

import android.util.Log
import eu.sesma.paraguas.domain.ForecastItem
import eu.sesma.paraguas.domain.WeatherData
import javax.inject.Inject

class MainActivityPresenter
@Inject
constructor(
    private val interactor: MainActivityInteractor
) {

    companion object {
        private val TAG = MainActivityPresenter::class.simpleName
    }

    private var decorator: MainActivityUserInterface? = null

    private val delegate = object : MainActivityUserInterface.Delegate {

        override fun onClick(forecastItem: ForecastItem) = interactor.navigateToDetail(forecastItem)

        override fun onRefresh() = interactor.refresh()
    }

    private val subscriber = object : MainActivityInteractor.RefreshSubscriber {
        override fun handleOnForecastResult(data: WeatherData) {
            val (_, currentWeather, astronomy, forecast) = data
            Log.d(TAG, forecast.toString())
            Log.d(TAG, astronomy.toString())
            Log.d(TAG, currentWeather.toString())
            Log.d(TAG, "City: ${interactor.city}")
            if (forecast != null) decorator?.showForecast(forecast)
            if (astronomy != null) decorator?.showCurrentAstronomy(astronomy)
            if (currentWeather != null) decorator?.showCurrentWeather(currentWeather)
            decorator?.setCity(interactor.city ?: "")
        }

        override fun onError(ex: Exception) {
            decorator?.showError(ex)
        }
    }

    fun initialize(decorator: MainActivityUserInterface) {
        interactor.initialize(subscriber)
        this.decorator = decorator
        this.decorator?.initialize(delegate)
    }

    fun onResume() = interactor.refresh()

    fun dispose() {
        interactor.dispose()
        decorator = null
    }
}
