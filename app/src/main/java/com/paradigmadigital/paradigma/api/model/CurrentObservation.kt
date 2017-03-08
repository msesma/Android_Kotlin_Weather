package com.paradigmadigital.paradigma.api.model

import com.google.gson.annotations.SerializedName

class CurrentObservation {

    @SerializedName("precip_1hr_metric")
    val precip1hrMetric: String? = null

    @SerializedName("icon")
    val icon: String? = null

    @SerializedName("temp_c")
    val tempC: Float = 0f
}

//        "temp_c": 19.1,
//        "precip_1hr_metric": " 0",
//        "icon": "partlycloudy"