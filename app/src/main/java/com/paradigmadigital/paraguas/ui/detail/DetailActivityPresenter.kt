package com.paradigmadigital.paraguas.ui.detail

import com.paradigmadigital.paraguas.domain.ForecastItem
import com.paradigmadigital.paraguas.ui.master.MainActivityPresenter
import javax.inject.Inject

class DetailActivityPresenter
@Inject
constructor() {

    val TAG = MainActivityPresenter::class.simpleName

    private var decorator: DetailActivityUserInterface? = null


    fun initialize(decorator: DetailActivityUserInterface, forecastItem: ForecastItem?) {
        this.decorator = decorator
        this.decorator?.initialize(forecastItem)
    }

    fun dispose() {
        this.decorator = null
    }
}
