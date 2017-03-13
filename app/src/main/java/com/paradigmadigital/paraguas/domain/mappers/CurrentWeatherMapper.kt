package com.paradigmadigital.paraguas.domain.mappers

import com.paradigmadigital.paraguas.api.model.ConditionsData
import com.paradigmadigital.paraguas.api.model.CurrentWeather
import javax.inject.Inject

class CurrentWeatherMapper
@Inject
constructor() : Mapper<CurrentWeather, ConditionsData> {
    override fun map(input: ConditionsData): CurrentWeather {
        return CurrentWeather(
                precip1hrMetric = input.currenObservation?.precip1hrMetric?.toFloat() ?: 0f,
                iconUrl = input.currenObservation?.iconUrl ?: "",
                temp = input.currenObservation?.tempC ?: 0f,
                feelsLike = input.currenObservation?.feelsLikeC ?: 0f,
                condition = input.currenObservation?.condition ?: ""
        )
    }
}