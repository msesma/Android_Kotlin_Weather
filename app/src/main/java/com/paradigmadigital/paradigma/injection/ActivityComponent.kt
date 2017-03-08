package com.paradigmadigital.paradigma.injection

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import com.paradigmadigital.paradigma.ui.MainActivity
import com.paradigmadigital.paradigma.platform.ActivityModule
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    //Exposed to sub-graphs.
    fun activity(): AppCompatActivity
}
