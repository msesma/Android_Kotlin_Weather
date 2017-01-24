package com.paradigmadigital.paradigma.platform

import android.app.Application
import com.paradigmadigital.paradigma.injection.ApplicationComponent
import com.paradigmadigital.paradigma.injection.DaggerApplicationComponent

class AndroidApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()

        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}
