package com.paradigmadigital.paradigma.platform

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.paradigmadigital.paradigma.injection.ActivityComponent
import com.paradigmadigital.paradigma.injection.ApplicationComponent
import com.paradigmadigital.paradigma.injection.DaggerActivityComponent

fun Activity.getRootView(): ViewGroup = (this.findViewById(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup

open class BaseActivity : AppCompatActivity() {

    private val applicationComponent: ApplicationComponent
        get() = (application as AndroidApplication).applicationComponent

    lateinit var activityComponent: ActivityComponent

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(ActivityModule(this))
                .build()
    }
}
