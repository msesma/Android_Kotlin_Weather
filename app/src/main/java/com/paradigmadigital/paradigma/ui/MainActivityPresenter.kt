package com.paradigmadigital.paradigma.ui

import javax.inject.Inject

class MainActivityPresenter
@Inject
constructor(private val interactor: MainActivityInteractor) {

    private var decorator: MainActivityUserInterface? = null

    private val delegate = object : MainActivityUserInterface.Delegate {

        override fun onRefreshButtonClick() {
            interactor.refresh()
        }
    }

    private val subscriber = object : MainActivityInteractor.RefreshSubscriber {

        override fun onResult(currentWeather: String, city: String) {
            decorator?.showMessage(currentWeather)
            decorator?.setCity(city)
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
