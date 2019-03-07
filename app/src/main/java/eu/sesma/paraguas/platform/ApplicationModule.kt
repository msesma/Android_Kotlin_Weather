package eu.sesma.paraguas.platform

import android.content.Context
import android.location.Geocoder
import android.preference.PreferenceManager
import eu.sesma.paraguas.domain.cache.CacheProvider
import dagger.Module
import dagger.Provides
import eu.sesma.paraguas.injection.PerActivity
import java.util.*
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return this.application
    }

    @Provides
    @Singleton
    internal fun provideSharedPreferences() = PreferenceManager.getDefaultSharedPreferences(application)

    @Provides
    @Singleton
    internal fun provideCacheProvider() = CacheProvider()

    @Provides
    fun provideGeocoder(context: Context) = Geocoder(context, Locale.getDefault())
}
