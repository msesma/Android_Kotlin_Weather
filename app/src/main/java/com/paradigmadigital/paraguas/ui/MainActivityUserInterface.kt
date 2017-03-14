package com.paradigmadigital.paraguas.ui

import com.paradigmadigital.paraguas.api.model.Astronomy
import com.paradigmadigital.paraguas.api.model.CurrentWeather

interface MainActivityUserInterface {

    fun initialize(delegate: Delegate)

    fun showError(errorMessage: String)

    fun showCurrentWeather(currentWeather: CurrentWeather)

    fun showCurrentAstronomy(astronomy: Astronomy)

    fun setCity(city: String)

    interface Delegate {

        fun onRefreshButtonClick()

    }
}
