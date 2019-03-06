package eu.sesma.paraguas.domain

data class WeatherData(
    val city: City? = null,
    val currentWeather: CurrentWeather? = null,
    val astronomy: Astronomy? = null,
    val forecast: List<ForecastItem>? = null
)