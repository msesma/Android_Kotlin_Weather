package com.paradigmadigital.paraguas.scheduler

import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.paradigmadigital.paraguas.log.DiskLogger
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class Scheduler
@Inject
constructor(
        val diskLogger: DiskLogger
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

        WorkManager.getInstance().enqueueUniquePeriodicWork(
                JOB_TAG,
                ExistingPeriodicWorkPolicy.REPLACE,
                forecastWorker)

        Log.d(TAG, "Launching worker")
        diskLogger.log(TAG, "Launching worker")
    }
}