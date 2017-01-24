package com.paradigmadigital.paradigma.api.model

import com.google.gson.annotations.SerializedName

class Wind {

    @SerializedName("speed")
    val speed: Double? = null

    @SerializedName("deg")
    val deg: Int? = null

    @SerializedName("gust")
    val gust: Double? = null

}
