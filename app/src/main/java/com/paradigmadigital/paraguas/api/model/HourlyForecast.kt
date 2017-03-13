package com.paradigmadigital.paraguas.api.model

import com.google.gson.annotations.SerializedName

class HourlyForecast {

    @SerializedName("FCTTIME")
    val fctTime: FctTime? = null

    @SerializedName("temp")
    val temp: Temp? = null

    @SerializedName("feelslike")
    val feelslike: Feelslike? = null

    @SerializedName("wspd")
    val wspd: Wspd? = null

    @SerializedName("qpf")
    val qpf: Qpf? = null

    @SerializedName("snow")
    val snow: Snow? = null

    @SerializedName("condition")
    val condition: String? = null

    @SerializedName("icon_url")
    val iconUrl: String? = null

    @SerializedName("humidity")
    val humidity: String? = null

    @SerializedName("pop")
    val pop: String? = null
}
