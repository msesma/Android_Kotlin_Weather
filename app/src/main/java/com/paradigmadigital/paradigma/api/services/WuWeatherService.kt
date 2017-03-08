package com.paradigmadigital.paradigma.api.services


import com.paradigmadigital.paradigma.api.model.WuAstronomyData
import com.paradigmadigital.paradigma.api.model.WuConditionsData
import com.paradigmadigital.paradigma.api.model.WuWeatherData
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface WuWeatherService {

    @GET("hourly/q/{countryCode}/{city}.json")
    fun getWeather(@Path("countryCode") countryCode: String, @Path("city") city: String): Observable<WuWeatherData>
//        url = "http://api.wunderground.com/api/93d0c442f87c0b10/hourly/q/UK/Glasgow.json";

    @GET("conditions/q/{countryCode}/{city}.json")
    fun getConditions(@Path("countryCode") countryCode: String, @Path("city") city: String): Observable<WuConditionsData>

    @GET("astronomy/q/{countryCode}/{city}.json")
    fun getAstronomy(@Path("countryCode") countryCode: String, @Path("city") city: String): Observable<WuAstronomyData>
}