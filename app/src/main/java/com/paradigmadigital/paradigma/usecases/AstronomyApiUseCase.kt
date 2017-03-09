package com.paradigmadigital.paradigma.usecases

import com.paradigmadigital.paradigma.api.model.WeatherData
import com.paradigmadigital.paradigma.api.services.WeatherService
import com.paradigmadigital.paradigma.usecases.ApiUseCase.Companion.URL
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class AstronomyApiUseCase
@Inject
constructor(client: OkHttpClient) : ApiUseCase {

    val service: WeatherService

    init {
        service = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build()
                .create(WeatherService::class.java)
    }

    fun execute(country: String = "ES", city: String): Observable<WeatherData> {
        return service.getWeather(country, city)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

}