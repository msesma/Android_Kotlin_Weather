package com.paradigmadigital.paraguas.platform

import android.app.Application
import com.paradigmadigital.paraguas.injection.ApplicationComponent
import com.paradigmadigital.paraguas.injection.DaggerApplicationComponent

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
