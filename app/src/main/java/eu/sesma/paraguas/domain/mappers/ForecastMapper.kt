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
        val astronomy = astronomyMapper.map(forecast.daily)
        val hourly = forecast.hourly?.dataPoints?.map { dataPoint ->
            ForecastItem(
//                            time = getTime(hourlyForecast.fctTime?.epoch ?: "0"),
//                            temp = hourlyForecast.temp?.metric?.toFloatOrNull() ?: 0f,
//                            feelslike = hourlyForecast.feelslike?.metric?.toFloatOrNull() ?: 0f,
//                            windSpeed = hourlyForecast.wspd?.metric?.toFloatOrNull() ?: 0f,
//                            rainProbability = hourlyForecast.pop?.toFloatOrNull() ?: 0f,
//                            rainQuantity = hourlyForecast.qpf?.metric?.toFloatOrNull() ?: 0f,
//                            snow = hourlyForecast.snow?.metric?.toFloatOrNull() ?: 0f,
//                            condition = hourlyForecast.condition ?: "",
//                            iconUrl = hourlyForecast.iconUrl ?: "",
//                            humidity = hourlyForecast.humidity?.toFloatOrNull() ?: 0f
            )
        }
        return WeatherData(city = city, currentWeather = currentWeather, astronomy = astronomy, forecast = hourly)
    }

//    fun getTime(epoch: String) = Date(epoch.toLong() * 1000)
}