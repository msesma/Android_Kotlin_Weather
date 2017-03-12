package com.paradigmadigital.paraguas.domain.mappers

import com.paradigmadigital.paraguas.api.model.Astronomy
import com.paradigmadigital.paraguas.api.model.AstronomyData
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class AstronomyMapper
@Inject
constructor() : Mapper<Astronomy, AstronomyData> {
    override fun map(input: AstronomyData): Astronomy {
        return Astronomy(
                ageOfMoon = input.moonPhase?.ageOfMoon?.toInt() ?: 0,
                sunrise = getTime(input.sunPhase?.sunrise?.hour ?: "00", input.sunPhase?.sunrise?.minute ?: "00"),
                sunset = getTime(input.sunPhase?.sunset?.hour ?: "00", input.sunPhase?.sunset?.minute ?: "00")
        )
    }

    private fun getTime(hour: String, minute: String): Date? {
        val format = SimpleDateFormat("HH mm")
        return format.parse("$hour $minute")
    }
}