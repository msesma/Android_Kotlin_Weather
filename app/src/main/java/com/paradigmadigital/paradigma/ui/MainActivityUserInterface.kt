package com.paradigmadigital.paradigma.ui

interface MainActivityUserInterface {

    fun initialize(delegate: Delegate, city: String)

    fun showError(errorMessage: String)

    fun showMessage(message: String)

    interface Delegate {

        fun onRefreshButtonClick()

    }
}
