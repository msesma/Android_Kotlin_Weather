package eu.sesma.paraguas.scheduler


import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import eu.sesma.paraguas.injection.ApplicationComponent
import eu.sesma.paraguas.injection.DaggerWorkerComponent
import eu.sesma.paraguas.log.DiskLogger
import eu.sesma.paraguas.platform.AndroidApplication
import eu.sesma.paraguas.platform.WorkerModule
import javax.inject.Inject


class ForecastWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

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
        var result = Result.success()

        forecastRetriever.getForecast()
            .toObservable<Void>()
            .blockingSubscribe({ result = Result.success() }, { result = Result.retry() })

        Log.d(TAG, "doWork finished $result")
        diskLogger.log(TAG, "doWork finished $result")
        return result
    }
}