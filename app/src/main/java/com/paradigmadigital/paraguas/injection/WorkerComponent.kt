package com.paradigmadigital.paraguas.injection

import android.app.Service
import androidx.work.Worker
import com.paradigmadigital.paraguas.platform.WorkerModule
import com.paradigmadigital.paraguas.scheduler.ForecastWorker
import dagger.Component

@PerWorker
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(WorkerModule::class))
interface WorkerComponent {

    fun inject(forecastWorker: ForecastWorker)

    //Exposed to sub-graphs.
    fun worker(): Worker
}
