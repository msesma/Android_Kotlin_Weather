package com.paradigmadigital.paradigma.splash

import javax.inject.Inject

class SplashActivityPresenter
@Inject
constructor(private val interactor: SplashActivityInteractor) {

    private var decorator: SplashActivityUserInterface? = null

    private var city: String? = null
    private val delegate = object : SplashActivityUserInterface.Delegate {

        override fun onRefreshButtonClick() {
            interactor.refresh(city ?: "")
        }
    }

    private val subscriber = object : SplashActivityInteractor.RefreshSubscriber {

        override fun onResult(currentWeather: String) {
            decorator?.showMessage(currentWeather)
        }

        override fun onError(ex: Exception) {
            decorator?.showError(ex.message ?: ex.toString())
        }
    }

    fun initialize(decorator: SplashActivityUserInterface) {
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
