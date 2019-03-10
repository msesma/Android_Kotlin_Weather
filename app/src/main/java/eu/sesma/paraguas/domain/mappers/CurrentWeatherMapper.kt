package eu.sesma.paraguas.domain.mappers

import eu.sesma.paraguas.api.model.DataPoint
import eu.sesma.paraguas.domain.CurrentWeather
import javax.inject.Inject

class CurrentWeatherMapper
@Inject
constructor() : Mapper<CurrentWeather, DataPoint?> {
    override fun map(input: DataPoint?): CurrentWeather {
        return CurrentWeather(
            precip1hrMetric = input?.precipIntensity ?: 0.0,
            iconName = input?.icon?.text ?: "",
            temp = input?.temperature ?: 0.0,
            feelsLike = input?.apparentTemperature ?: 0.0,
            condition = input?.summary ?: ""
        )
    }
}