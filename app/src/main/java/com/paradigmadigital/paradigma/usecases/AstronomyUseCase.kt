package com.paradigmadigital.paradigma.usecases

import com.paradigmadigital.paradigma.api.model.WuWeatherData
import com.paradigmadigital.paradigma.api.services.WuWeatherService
import com.paradigmadigital.paradigma.usecases.UseCase.Companion.URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject


class AstronomyUseCase
@Inject
constructor(client: OkHttpClient) : UseCase {

    val service: WuWeatherService

    init {
        service = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build()
                .create(WuWeatherService::class.java)
    }

    fun execute(country: String = "ES", city: String): Observable<WuWeatherData> {
        return service.getWeather(country, city)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

}