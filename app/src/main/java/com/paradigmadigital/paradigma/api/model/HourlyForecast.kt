package com.paradigmadigital.paradigma.api.model

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
