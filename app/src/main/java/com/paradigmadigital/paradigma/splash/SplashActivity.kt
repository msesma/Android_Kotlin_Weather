package com.paradigmadigital.paradigma.splash

import android.os.Bundle
import android.view.ViewGroup
import com.paradigmadigital.paradigma.R
import com.paradigmadigital.paradigma.platform.BaseActivity
import javax.inject.Inject


class SplashActivity : BaseActivity() {

    @Inject
    lateinit var delegate: Delegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        activityComponent.inject(this)

        val rootView = (this.findViewById(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup //TODO simplify
        delegate.onCreate(rootView)
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
