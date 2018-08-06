package eu.sesma.paraguas.api.model

import com.google.gson.annotations.SerializedName

class WeatherData {

    @SerializedName("response")
    val response: Response? = null

    @SerializedName("hourly_forecast")
    val hourlyForecast: List<HourlyForecast>? = null

}
