package com.paradigmadigital.paradigma.injection

import android.util.Log
import com.paradigmadigital.paradigma.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
class ApiModule() {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d("Retrofit", message) })
        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }

    @Provides
    internal fun provideOkHttpClient(
            loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
         return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
    }
}
