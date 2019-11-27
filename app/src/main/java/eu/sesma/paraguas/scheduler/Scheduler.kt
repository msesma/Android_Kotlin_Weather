package eu.sesma.paraguas.scheduler

import android.content.Context
import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import eu.sesma.paraguas.log.DiskLogger
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class Scheduler
@Inject
constructor(
    private val diskLogger: DiskLogger,
    private val context: Context
) {
    companion object {
        private val TAG = Scheduler::class.simpleName
    }

    private val INTERVAL = 3600L
    private val JOB_TAG = "forecast-job-tag"

    fun start() {
        val forecastWorker = PeriodicWorkRequest
            .Builder(ForecastWorker::class.java, INTERVAL, TimeUnit.SECONDS)
            .addTag(JOB_TAG)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            JOB_TAG,
            ExistingPeriodicWorkPolicy.REPLACE,
            forecastWorker
        )

        Log.d(TAG, "Launching worker")
        diskLogger.log(TAG, "Launching worker")
    }
}