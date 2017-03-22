package com.paradigmadigital.paraguas.scheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import javax.inject.Inject



class Scheduler
@Inject
constructor(context: Context) {

    private val SECONDS_MIN = 0
    private val SECONDS_MAX = 15
    private val JOB_TAG = "forecast-job-tag"

    private val scheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler;
    private val jobinfo: JobInfo

    init {
        jobinfo = JobInfo.Builder(1, ComponentName(context.getPackageName(),
                ForecastJobService::class.java.getName()))
                .setPeriodic(60000)
                .setPersisted(true)
                .build()
    }

    fun dispatch() {
        scheduler.schedule(jobinfo)
    }
}