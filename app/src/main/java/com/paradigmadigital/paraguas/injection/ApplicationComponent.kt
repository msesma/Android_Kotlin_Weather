package com.paradigmadigital.paraguas.injection

import android.content.Context
import android.content.SharedPreferences
import com.paradigmadigital.paraguas.ui.MainActivity
import com.paradigmadigital.paraguas.platform.ApplicationModule
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        ApiModule::class))
interface ApplicationComponent {

    //Exposed to sub-graphs
    fun provideContext(): Context

    fun provideSharedPreferences(): SharedPreferences

    fun provideOkHttpClient(): OkHttpClient
}
