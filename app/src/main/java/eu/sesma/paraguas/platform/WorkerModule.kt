package eu.sesma.paraguas.platform

import androidx.work.Worker
import eu.sesma.paraguas.injection.PerWorker
import dagger.Module
import dagger.Provides

@Module
class WorkerModule(private val worker: Worker) {

    @Provides
    @PerWorker
    internal fun worker(): Worker = this.worker
}

