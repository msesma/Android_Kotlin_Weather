package eu.sesma.paraguas.api.services


import eu.sesma.paraguas.api.model.Forecast
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface WeatherService {
    @GET("forecast/{key}/{latitude},{longitude}")
    fun getForecast(
        @Path("key") key: String,
        @Path("latitude") latitude: String,
        @Path("longitude") longitude: String,
        @QueryMap queryParameter: Map<String, String>,
        @Header("Cache-Control") cacheControl: String
    ): Single<Forecast>
}