package eu.sesma.paraguas.api.model

import com.google.gson.annotations.SerializedName

class MoonPhase {

    @SerializedName("ageOfMoon")
    val ageOfMoon: String? = null

    @SerializedName("sunrise")
    val sunrise: Sunrise? = null

    @SerializedName("sunset")
    val sunset: Sunset? = null
}
