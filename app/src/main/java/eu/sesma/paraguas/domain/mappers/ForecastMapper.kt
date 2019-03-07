package eu.sesma.paraguas.domain.mappers

import eu.sesma.paraguas.api.ds_model.Forecast
import eu.sesma.paraguas.domain.City
import eu.sesma.paraguas.domain.ForecastItem
import eu.sesma.paraguas.domain.WeatherData
import javax.inject.Inject

class ForecastMapper
@Inject
constructor(
    private val currentWeatherMapper: CurrentWeatherMapper,
    private val astronomyMapper: AstronomyMapper
) : Mapper<WeatherData, Pair<Forecast, City>> {
    override fun map(input: Pair<Forecast, City>): WeatherData {
        val (forecast, city) = input
        val currentWeather = currentWeatherMapper.map(forecast.currently)
        val astronomy = astronomyMapper.map(forecast.currently)
        val hourly = forecast.hourly?.dataPoints?.map { dataPoint ->
            ForecastItem(
                time = dataPoint.time,
                temp = dataPoint.temperature ?: 0.0,
                feelslike = dataPoint.apparentTemperature ?: 0.0,
                windSpeed = dataPoint.windSpeed ?: 0.0,
                rainProbability = dataPoint.precipProbability ?: 0.0,
                rainQuantity = dataPoint.precipIntensity ?: 0.0,
//                snow = dataPoint.apparentTemperature ?: 0.0,
                condition = dataPoint.summary ?: "",
                iconUrl = dataPoint.icon?.text ?: "",
                humidity = dataPoint.humidity ?: 0.0
            )
        }
        return WeatherData(city = city, currentWeather = currentWeather, astronomy = astronomy, forecast = hourly)
    }
}