package eu.sesma.paraguas.api.services


import eu.sesma.paraguas.api.ds_model.Forecast
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface WeatherService {

//    @GET("hourly/q/{countryCode}/{city}.json")
//    fun getWeather(@Path("countryCode") countryCode: String, @Path("city") city: String): Single<WeatherData>
//
//    @GET("conditions/q/{countryCode}/{city}.json")
//    fun getConditions(@Path("countryCode") countryCode: String, @Path("city") city: String): Single<ConditionsData>
//
//    @GET("astronomy/q/{countryCode}/{city}.json")
//    fun getAstronomy(@Path("countryCode") countryCode: String, @Path("city") city: String): Single<AstronomyData>
//
//    @GET("geolookup/q/{latitude},{longitude}.json")
//    fun getGeoLookUp(@Path("latitude") latitude: String, @Path("longitude") longitude: String): Single<GeoLookUp>

    @GET("/{latitude},{longitude}")
    fun getForecast(
        @Path("latitude") latitude: String,
        @Path("longitude") longitude: String,
        @QueryMap queryParameter: Map<String, String>,
        @Header("Cache-Control") cacheControl: String
    ): Single<Forecast>
}