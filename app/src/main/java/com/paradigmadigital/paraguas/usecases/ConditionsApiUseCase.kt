package com.paradigmadigital.paraguas.usecases

import com.paradigmadigital.paraguas.api.Endpoint
import com.paradigmadigital.paraguas.api.model.CurrentWeather
import com.paradigmadigital.paraguas.api.services.WeatherService
import com.paradigmadigital.paraguas.domain.mappers.CurrentWeatherMapper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class ConditionsApiUseCase
@Inject
constructor(client: OkHttpClient, endpoint: Endpoint, val mapper: CurrentWeatherMapper) {

    val service: WeatherService

    init {
        service = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(endpoint.URL)
                .build()
                .create(WeatherService::class.java)
    }

    fun execute(country: String = "ES", city: String): Observable<CurrentWeather> {
        return service.getConditions(country, city)
                .map { mapper.map(it) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

}