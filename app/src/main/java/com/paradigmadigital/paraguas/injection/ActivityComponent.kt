package com.paradigmadigital.paraguas.injection

import android.support.v7.app.AppCompatActivity
import com.paradigmadigital.paraguas.platform.ActivityModule
import com.paradigmadigital.paraguas.scheduler.ForecastJobService
import com.paradigmadigital.paraguas.ui.detail.DetailActivity
import com.paradigmadigital.paraguas.ui.master.MainActivity
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(detailActivity: DetailActivity)

    //Exposed to sub-graphs.
    fun activity(): AppCompatActivity
}
