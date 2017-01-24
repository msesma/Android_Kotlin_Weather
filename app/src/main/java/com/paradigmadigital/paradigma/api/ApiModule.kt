package com.paradigmadigital.paradigma.injection

import com.paradigmadigital.paradigma.api.interceptors.HttpLogger
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
class ApiModule() {

    @Provides
    internal fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor(HttpLogger())
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
    }

}
