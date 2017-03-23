package com.paradigmadigital.paraguas.api.model

import com.google.gson.annotations.SerializedName

class CurrentObservation {

    @SerializedName("precip_1hr_metric")
    val precip1hrMetric: String? = null

    @SerializedName("icon_url")
    val iconUrl: String? = null

    @SerializedName("icon")
    val iconName: String? = null

    @SerializedName("temp_c")
    val tempC: Float = 0f

    @SerializedName("feelslike_c")
    val feelsLikeC: Float = 0f

    @SerializedName("weather")
    val condition: String = ""
}
