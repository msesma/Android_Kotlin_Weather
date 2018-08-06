package eu.sesma.paraguas.platform

import android.app.Application
import eu.sesma.paraguas.injection.ApplicationComponent
import eu.sesma.paraguas.injection.DaggerApplicationComponent

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
