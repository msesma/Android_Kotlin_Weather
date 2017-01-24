package com.paradigmadigital.paradigma.api.model

import com.google.gson.annotations.SerializedName

class Main {

    @SerializedName("temp")
    val temp: Double? = null

    @SerializedName("pressure")
    val pressure: Int? = null

    @SerializedName("humidity")
    val humidity: Int? = null

    @SerializedName("temp_min")
    val tempMin: Double? = null

    @SerializedName("temp_max")
    val tempMax: Double? = null
}
