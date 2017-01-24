package com.paradigmadigital.paradigma.injection

import android.app.Activity
import com.paradigmadigital.paradigma.SplashActivity
import com.paradigmadigital.paradigma.platform.ActivityModule
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(splashActivity: SplashActivity)

    //Exposed to sub-graphs.
    fun activity(): Activity
}
