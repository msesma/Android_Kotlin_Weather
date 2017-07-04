package com.paradigmadigital.paraguas.api.services


import com.paradigmadigital.paraguas.api.model.AstronomyData
import com.paradigmadigital.paraguas.api.model.ConditionsData
import com.paradigmadigital.paraguas.api.model.GeoLookUp
import com.paradigmadigital.paraguas.api.model.WeatherData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("hourly/q/{countryCode}/{city}.json")
    fun getWeather(@Path("countryCode") countryCode: String, @Path("city") city: String): Single<WeatherData>

    @GET("conditions/q/{countryCode}/{city}.json")
    fun getConditions(@Path("countryCode") countryCode: String, @Path("city") city: String): Single<ConditionsData>

    @GET("astronomy/q/{countryCode}/{city}.json")
    fun getAstronomy(@Path("countryCode") countryCode: String, @Path("city") city: String): Single<AstronomyData>

    @GET("geolookup/q/{latitude},{longitude}.json")
    fun getGeoLookUp(@Path("latitude") latitude: String, @Path("longitude") longitude: String): Single<GeoLookUp>
}