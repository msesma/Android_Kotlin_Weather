package com.paradigmadigital.paraguas.scheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import com.paradigmadigital.paraguas.injection.ApplicationComponent
import com.paradigmadigital.paraguas.injection.ServiceComponent
import com.paradigmadigital.paraguas.platform.AndroidApplication
import javax.inject.Inject


class ForecastJobService : JobService() {

    val TAG = ForecastJobService::class.simpleName

    lateinit var serviceComponent: ServiceComponent
    private val applicationComponent: ApplicationComponent
        get() = (application as AndroidApplication).applicationComponent

    @Inject
    lateinit var forecastRetriever: ForecastRetriever

    override fun onCreate() {
        super.onCreate()
//        serviceComponent = DaggerServiceComponent.builder()
//                .applicationComponent(applicationComponent)
//                .serviceModule(ServiceModule(this))
//                .build()
//        serviceComponent.inject(this)

    }

    override fun onStartJob(job: JobParameters): Boolean {
        Log.d(TAG, "onStartJob")
        forecastRetriever.start()
        return true
    }

    override fun onStopJob(job: JobParameters): Boolean {
        Log.d(TAG, "onStopJob")
        return false
    }

}