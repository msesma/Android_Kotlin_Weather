package com.paradigmadigital.paraguas.platform

import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    internal fun provideContext() = this.application

    @Provides
    @Singleton
    internal fun provideSharedPreferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)
}
