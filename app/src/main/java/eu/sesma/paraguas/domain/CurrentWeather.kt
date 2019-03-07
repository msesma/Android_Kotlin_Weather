package eu.sesma.paraguas.domain

data class CurrentWeather(
        val precip1hrMetric: Double = 0.0,
        val iconUrl: String = "",
        val iconName: String = "",
        val temp: Double = 0.0,
        val feelsLike: Double = 0.0,
        val condition: String = ""
)
