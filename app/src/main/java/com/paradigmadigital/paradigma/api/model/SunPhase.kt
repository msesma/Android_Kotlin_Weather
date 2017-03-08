package com.paradigmadigital.paradigma.api.model

import com.google.gson.annotations.SerializedName

class SunPhase {

    @SerializedName("sunrise")
    val sunrise: Sunrise? = null

    @SerializedName("sunset")
    val sunset: Sunset? = null
}
