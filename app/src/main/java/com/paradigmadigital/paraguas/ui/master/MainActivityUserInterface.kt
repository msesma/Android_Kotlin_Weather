package com.paradigmadigital.paraguas.ui.master

import com.paradigmadigital.paraguas.domain.Astronomy
import com.paradigmadigital.paraguas.domain.CurrentWeather
import com.paradigmadigital.paraguas.domain.ForecastItem

interface MainActivityUserInterface {

    fun initialize(delegate: Delegate)

    fun showError(errorMessage: String)

    fun showCurrentWeather(currentWeather: CurrentWeather)

    fun showCurrentAstronomy(astronomy: Astronomy)

    fun showForecast(forecast: List<ForecastItem>)

    fun setCity(city: String)

    interface Delegate {

        fun onRefresh()

        fun onClick(forecastItem: ForecastItem)

    }
}
