package com.paradigmadigital.paradigma.ui

import javax.inject.Inject

class MainActivityPresenter
@Inject
constructor(private val interactor: MainActivityInteractor) {

    private var decorator: MainActivityUserInterface? = null

    private var city: String? = null
    private val delegate = object : MainActivityUserInterface.Delegate {

        override fun onRefreshButtonClick() {
            interactor.refresh(city ?: "")
        }
    }

    private val subscriber = object : MainActivityInteractor.RefreshSubscriber {

        override fun onResult(currentWeather: String) {
            decorator?.showMessage(currentWeather)
        }

        override fun onError(ex: Exception) {
            decorator?.showError(ex.message ?: ex.toString())
        }
    }

    fun initialize(decorator: MainActivityUserInterface) {
        interactor.initialize(subscriber)
        city = interactor.getCity()
        this.decorator = decorator
        this.decorator?.initialize(delegate, city ?: "")
    }

    fun onResume() {
        interactor.refresh(city ?: "")
    }

    fun dispose() {
        this.decorator = null
    }
}
