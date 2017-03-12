package com.paradigmadigital.paraguas.api.model

data class CurrentWeather(
        val precip1hrMetric: Float = 0f,
        val iconUrl: String = "",
        val temp: Float = 0f,
        val feelsLike: Float = 0f,
        val condition: String = ""
)
