package com.paradigmadigital.paraguas.injection

import android.app.Service
import com.paradigmadigital.paraguas.platform.ServiceModule
import com.paradigmadigital.paraguas.scheduler.ForecastJobService
import dagger.Component

@PerService
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ServiceModule::class))
interface ServiceComponent {

    fun inject(forecastJobService: ForecastJobService)

    //Exposed to sub-graphs.
    fun service(): Service
}
