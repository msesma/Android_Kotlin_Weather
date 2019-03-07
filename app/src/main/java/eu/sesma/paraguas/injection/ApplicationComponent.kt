package eu.sesma.paraguas.injection

import android.content.Context
import android.content.SharedPreferences
import android.location.Geocoder
import com.squareup.picasso.Picasso
import dagger.Component
import eu.sesma.paraguas.api.ApiModule
import eu.sesma.paraguas.api.services.WeatherService
import eu.sesma.paraguas.domain.cache.CacheProvider
import eu.sesma.paraguas.platform.ApplicationModule
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ApiModule::class])
interface ApplicationComponent {

    //Exposed to sub-graphs
    fun provideContext(): Context

    fun provideSharedPreferences(): SharedPreferences

    fun provideOkHttpClient(): OkHttpClient

    fun provideWeatherService(): WeatherService

    fun provideCacheControl(): CacheControl

    fun provideGeocoder(): Geocoder

    fun providePicasso(): Picasso

    fun provideCacheProvider(): CacheProvider
}
