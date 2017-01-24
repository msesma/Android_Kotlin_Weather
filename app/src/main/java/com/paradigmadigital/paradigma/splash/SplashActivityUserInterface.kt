package com.paradigmadigital.paradigma.splash

interface SplashActivityUserInterface {

    fun initialize(delegate: Delegate, city: String)

    fun showError(errorMessage: String)

    fun showMessage(message: String)

    interface Delegate {

        fun onRefreshButtonClick()

    }
}
