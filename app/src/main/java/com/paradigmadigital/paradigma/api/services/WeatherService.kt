package com.paradigmadigital.paradigma.api.services


import com.paradigmadigital.paradigma.api.model.AstronomyData
import com.paradigmadigital.paradigma.api.model.ConditionsData
import com.paradigmadigital.paradigma.api.model.GeoLookUp
import com.paradigmadigital.paradigma.api.model.WeatherData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("hourly/q/{countryCode}/{city}.json")
    fun getWeather(@Path("countryCode") countryCode: String, @Path("city") city: String): Observable<WeatherData>
//        url = "http://api.wunderground.com/api/93d0c442f87c0b10/hourly/q/UK/Glasgow.json";

    @GET("conditions/q/{countryCode}/{city}.json")
    fun getConditions(@Path("countryCode") countryCode: String, @Path("city") city: String): Observable<ConditionsData>

    @GET("astronomy/q/{countryCode}/{city}.json")
    fun getAstronomy(@Path("countryCode") countryCode: String, @Path("city") city: String): Observable<AstronomyData>

    @GET("geolookup/q/{latitude},{longitude}.json")
    fun getGeoLookUp(@Path("latitude") latitude: String, @Path("longitude") longitude: String): Observable<GeoLookUp>
}