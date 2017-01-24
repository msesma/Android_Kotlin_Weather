package com.paradigmadigital.paradigma.api.services


import com.paradigmadigital.paradigma.api.model.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface CurrentWeatherService {

    @GET("weather?")
    fun getCurrentWeather(@Query("APPID") appId: String, @Query("q") city: String): Observable<WeatherData>
}