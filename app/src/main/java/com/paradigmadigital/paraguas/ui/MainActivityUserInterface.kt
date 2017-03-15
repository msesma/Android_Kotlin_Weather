package com.paradigmadigital.paraguas.ui

import com.paradigmadigital.paraguas.api.model.Astronomy
import com.paradigmadigital.paraguas.api.model.CurrentWeather
import com.paradigmadigital.paraguas.api.model.ForecastItem

interface MainActivityUserInterface {

    fun initialize(delegate: Delegate)

    fun showError(errorMessage: String)

    fun showCurrentWeather(currentWeather: CurrentWeather)

    fun showCurrentAstronomy(astronomy: Astronomy)

    fun showForecast(forecast: List<ForecastItem>)

    fun setCity(city: String)

    interface Delegate {

        fun onRefresh()

    }
}
