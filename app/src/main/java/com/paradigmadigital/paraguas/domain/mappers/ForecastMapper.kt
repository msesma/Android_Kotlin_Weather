package com.paradigmadigital.paraguas.domain.mappers

import com.paradigmadigital.paraguas.api.model.ForecastItem
import com.paradigmadigital.paraguas.api.model.HourlyForecast
import com.paradigmadigital.paraguas.api.model.WeatherData
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ForecastMapper
@Inject
constructor() : Mapper<List<ForecastItem>, WeatherData> {
    override fun map(input: WeatherData): List<ForecastItem> {
        val forecast = mutableListOf<ForecastItem>()
        val apiForecast = input.hourlyForecast ?: listOf<HourlyForecast>()
        for (hourlyForecast in apiForecast) {
            forecast.add(
                    ForecastItem(
                            time = getTime(hourlyForecast.fctTime?.weekdayNameAbbrev ?: "Mon",
                                    hourlyForecast.fctTime?.hour ?: "00"),
                            temp = hourlyForecast.temp?.metric?.toFloat() ?: 0f,
                            feelslike = hourlyForecast.feelslike?.metric?.toFloat() ?: 0f,
                            windSpeed = hourlyForecast.wspd?.metric?.toFloat() ?: 0f,
                            rainProbability = hourlyForecast.qpf?.metric?.toFloat() ?: 0f,
                            rainQuantity = hourlyForecast.pop?.toFloat() ?: 0f,
                            snow = hourlyForecast.snow?.metric?.toFloat() ?: 0f,
                            condition = hourlyForecast.condition ?: "",
                            iconUrl = hourlyForecast.iconUrl ?: "",
                            humidity = hourlyForecast.humidity?.toFloat() ?: 0f
                    )
            )
        }
        return forecast
    }

    private fun getTime(day: String, hour: String): Date? {
        val format = SimpleDateFormat("EEE mm")
        return format.parse("$day $hour")
    }
}