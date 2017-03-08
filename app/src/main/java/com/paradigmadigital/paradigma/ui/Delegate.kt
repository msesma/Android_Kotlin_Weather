package com.paradigmadigital.paradigma.ui

import android.content.Intent
import android.view.View

import javax.inject.Inject

class Delegate
@Inject
constructor(private val decorator: MainActivityDecorator,
            private val presenter: MainActivityPresenter) {

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
