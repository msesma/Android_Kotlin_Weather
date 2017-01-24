package com.paradigmadigital.paradigma

import com.paradigmadigital.paradigma.api.model.WeatherData
import com.paradigmadigital.paradigma.api.services.CurrentWeatherService
import es.tid.gconnect.analytics.evs.HttpLogger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class CurrentWeatherUseCase {

    companion object {
        val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        val URL = "http://api.openweathermap.org/data/2.5/"
    }

    lateinit var service: CurrentWeatherService

    init {
        val logging = HttpLoggingInterceptor(HttpLogger())
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

        val retrofit = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build()
        service = retrofit.create(CurrentWeatherService::class.java)
    }

    fun execute(city: String): Observable<WeatherData> {
        val observable = service.getCurrentWeather(APP_ID, city)

        return observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

}