package com.paradigmadigital.paraguas.platform

import android.app.Service
import com.paradigmadigital.paraguas.injection.PerService
import dagger.Module
import dagger.Provides

@Module
class ServiceModule(private val service: Service) {

    @Provides
    @PerService
    internal fun service(): Service = this.service
}

