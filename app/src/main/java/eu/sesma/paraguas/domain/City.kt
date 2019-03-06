package eu.sesma.paraguas.domain

import android.location.Location

data class City(
        val city: String = "",
        val countryCode: String = "",
        val location: Location
)
