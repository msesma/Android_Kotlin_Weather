package eu.sesma.paraguas.ui.master

import eu.sesma.paraguas.domain.Astronomy
import eu.sesma.paraguas.domain.CurrentWeather
import eu.sesma.paraguas.domain.ForecastItem

interface MainActivityUserInterface {

    fun initialize(delegate: Delegate)

    fun showError(error: Exception)

    fun showCurrentWeather(currentWeather: CurrentWeather)

    fun showCurrentAstronomy(astronomy: Astronomy)

    fun showForecast(forecast: List<ForecastItem>)

    fun setCity(city: String)

    interface Delegate {

        fun onRefresh()

        fun onClick(forecastItem: ForecastItem)

    }
}
