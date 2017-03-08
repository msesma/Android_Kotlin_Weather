package com.paradigmadigital.paradigma.ui

import android.os.Bundle
import com.paradigmadigital.paradigma.R
import com.paradigmadigital.paradigma.platform.BaseActivity
import com.paradigmadigital.paradigma.platform.getRootView
import javax.inject.Inject


class MainActivity : BaseActivity() {

    @Inject
    lateinit var delegate: Delegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        activityComponent.inject(this)

        delegate.onCreate(this.getRootView())
    }

    override fun onResume() {
        super.onResume()
        delegate.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        delegate.onDestroy()
    }
}
