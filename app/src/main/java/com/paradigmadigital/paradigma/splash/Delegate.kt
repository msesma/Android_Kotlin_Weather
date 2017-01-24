package com.paradigmadigital.paradigma.splash

import android.content.Intent
import android.view.View

import javax.inject.Inject

class Delegate
@Inject
constructor(private val decorator: SplashActivityDecorator,
            private val presenter: SplashActivityPresenter) {

    fun onCreate(view: View) {
        decorator.bind(view)
        presenter.initialize(decorator)
    }

    fun onResume() {
        presenter.onResume()
    }

    fun onDestroy() {
        presenter.dispose()
        decorator.dispose()
    }
}
