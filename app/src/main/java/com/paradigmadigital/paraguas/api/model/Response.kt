package com.paradigmadigital.paraguas.api.model

import com.google.gson.annotations.SerializedName

class Response {

    @SerializedName("version")
    val version: String? = null

    @SerializedName("termsofService")
    val termsofService: String? = null
}
// "response": {
// "version": "0.1",
// "termsofService": "http://www.wunderground.com/weather/api/d/terms.html",
// "features": {
// "hourly": 1
// }
// },
