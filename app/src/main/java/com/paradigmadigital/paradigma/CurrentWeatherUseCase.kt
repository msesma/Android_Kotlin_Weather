package com.paradigmadigital.paradigma

import com.paradigmadigital.paradigma.api.model.WeatherData
import com.paradigmadigital.paradigma.api.services.CurrentWeatherService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject


class CurrentWeatherUseCase @Inject constructor(client: OkHttpClient) {

    companion object {
        val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        val URL = "http://api.openweathermap.org/data/2.5/"
    }

    val service: CurrentWeatherService

    init {
        service = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build()
                .create(CurrentWeatherService::class.java)
    }

    fun execute(city: String): Observable<WeatherData> {
        return service.getCurrentWeather(APP_ID, city)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

}