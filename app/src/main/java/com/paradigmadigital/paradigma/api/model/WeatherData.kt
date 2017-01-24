package com.paradigmadigital.paradigma.api.model

import com.google.gson.annotations.SerializedName

class WeatherData {

    @SerializedName("coord")
    val coord: Coord? = null

    @SerializedName("weather")
    val weather: List<Weather>? = null

    @SerializedName("base")
    val base: String? = null

    @SerializedName("main")
    val main: Main? = null

    @SerializedName("wind")
    val wind: Wind? = null

    @SerializedName("rain")
    val rain: Rain? = null

    @SerializedName("dt")
    val dt: Int? = null

    @SerializedName("sys")
    val sys: Sys? = null

    @SerializedName("id")
    val id: Int? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("cod")
    val cod: Int? = null

}
