package com.paradigmadigital.paraguas.scheduler


import android.util.Log
import com.firebase.jobdispatcher.JobParameters
import com.firebase.jobdispatcher.JobService
import com.paradigmadigital.paraguas.injection.ApplicationComponent
import com.paradigmadigital.paraguas.injection.DaggerServiceComponent
import com.paradigmadigital.paraguas.log.DiskLogger
import com.paradigmadigital.paraguas.platform.AndroidApplication
import com.paradigmadigital.paraguas.platform.ServiceModule
import javax.inject.Inject


class ForecastJobService : JobService() {

    val TAG = ForecastJobService::class.simpleName!!

    private val applicationComponent: ApplicationComponent
        get() = (application as AndroidApplication).applicationComponent

    @Inject
    lateinit var forecastRetriever: ForecastRetriever
    @Inject
    lateinit var diskLogger: DiskLogger

    override fun onCreate() {
        super.onCreate()
        DaggerServiceComponent.builder()
                .applicationComponent(applicationComponent)
                .serviceModule(ServiceModule(this))
                .build()
                .inject(this)

    }

    override fun onStartJob(job: JobParameters): Boolean {
        Log.d(TAG, "onStartJob")
        diskLogger.log(TAG, "onStartJob")
        forecastRetriever.start(this, job)
        return true
    }

    override fun onStopJob(job: JobParameters): Boolean {
        Log.d(TAG, "onStopJob")
        diskLogger.log(TAG, "onStopJob")
        return false
    }

}