package com.paradigmadigital.paraguas.api.model

import java.util.*

data class Astronomy(
        val timestamp: Long = 0,
        val ageOfMoon: Int? = null,
        val sunrise: Date? = null,
        val sunset: Date? = null
)
