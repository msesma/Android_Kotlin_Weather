package com.paradigmadigital.paraguas.platform

import androidx.work.Worker
import com.paradigmadigital.paraguas.injection.PerWorker
import dagger.Module
import dagger.Provides

@Module
class WorkerModule(private val worker: Worker) {

    @Provides
    @PerWorker
    internal fun worker(): Worker = this.worker
}

