package com.paradigmadigital.paraguas.scheduler


import android.util.Log
import androidx.work.Worker
import com.paradigmadigital.paraguas.injection.ApplicationComponent
import com.paradigmadigital.paraguas.injection.DaggerWorkerComponent
import com.paradigmadigital.paraguas.log.DiskLogger
import com.paradigmadigital.paraguas.platform.AndroidApplication
import com.paradigmadigital.paraguas.platform.WorkerModule
import javax.inject.Inject


class ForecastWorker : Worker() {

    val TAG = ForecastWorker::class.simpleName!!

    private val applicationComponent: ApplicationComponent
        get() = (applicationContext as AndroidApplication).applicationComponent

    @Inject
    lateinit var forecastRetriever: ForecastRetriever
    @Inject
    lateinit var diskLogger: DiskLogger

    override fun doWork(): Result {
        DaggerWorkerComponent.builder()
                .applicationComponent(applicationComponent)
                .workerModule(WorkerModule(this))
                .build()
                .inject(this)

        Log.d(TAG, "doWork")
        diskLogger.log(TAG, "doWork")
        var result = Result.SUCCESS

        forecastRetriever.getForecast()
                .blockingSubscribe({ result = Result.SUCCESS }, { result = Result.RETRY })

        Log.d(TAG, "doWork finished $result")
        diskLogger.log(TAG, "doWork finished $result")
        return result
    }
}