package com.paradigmadigital.paradigma.ui

interface MainActivityUserInterface {

    fun initialize(delegate: Delegate)

    fun showError(errorMessage: String)

    fun showMessage(message: String)

    fun setCity(city: String)

    interface Delegate {

        fun onRefreshButtonClick()

    }
}
