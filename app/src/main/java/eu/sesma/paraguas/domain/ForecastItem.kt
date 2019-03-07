package eu.sesma.paraguas.domain

import java.io.Serializable
import java.util.*

data class ForecastItem(
        val time: Date? = null,
        val temp: Double = 0.0,
        val feelslike: Double = 0.0,
        val windSpeed: Double = 0.0,
        val rainQuantity: Double = 0.0,
        val rainProbability: Double = 0.0,
        val snow: Double = 0.0,
        val condition: String = "",
        val iconUrl: String = "",
        val humidity: Double? = 0.0
) : Serializable
