package eu.sesma.paraguas.injection

import android.app.Service
import androidx.work.Worker
import eu.sesma.paraguas.platform.WorkerModule
import eu.sesma.paraguas.scheduler.ForecastWorker
import dagger.Component

@PerWorker
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(WorkerModule::class))
interface WorkerComponent {

    fun inject(forecastWorker: ForecastWorker)

    //Exposed to sub-graphs.
    fun worker(): Worker
}
