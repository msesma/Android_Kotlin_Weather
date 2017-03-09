package com.paradigmadigital.paraguas.injection

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import com.paradigmadigital.paraguas.ui.MainActivity
import com.paradigmadigital.paraguas.platform.ActivityModule
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    //Exposed to sub-graphs.
    fun activity(): AppCompatActivity
}
