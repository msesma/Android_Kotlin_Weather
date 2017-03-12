package com.paradigmadigital.paraguas.api.model

import java.util.*

data class ForecastItem(
        val time: Date? = null,
        val temp: Float = 0f,
        val feelslike: Float = 0f,
        val windSpeed: Float = 0f,
        val rainQuantity: Float = 0f,
        val rainProbability: Float? = 0f,
        val snow: Float = 0f,
        val condition: String = "",
        val iconUrl: String = "",
        val humidity: Float? = 0f
)
// "hourly_forecast": [
// {
// "FCTTIME": {
// .......
// },
// "temp": {
// .......
// },
// "dewpoint": {
// "english": "27",
// "metric": "-2"
// },
// "condition": "Clear",
// "icon": "clear",
// "icon_url": "http://icons-ak.wxug.com/i/c/k/clear.gif",
// "fctcode": "1",
// "sky": "13",
// "wspd": {
// .......
// },
// "wdir": {
// "dir": "NW",
// "degrees": "308"
// },
// "wx": "",
// "uvi": "3",
// "humidity": "50",
// "windchill": {
// "english": "-9998",
// "metric": "-9998"
// },
// "heatindex": {
// "english": "-9998",
// "metric": "-9998"
// },
// "feelslike": {
// .......
// },
// "qpf": {
// .......
// },
// "snow": {
// .......
// },
// "pop": "0",
// "mslp": {
// "english": "30.04",
// "metric": "1017"
// }
// },
