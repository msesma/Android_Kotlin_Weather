package eu.sesma.paraguas.domain.mappers

import eu.sesma.paraguas.api.ds_model.DataBlock
import eu.sesma.paraguas.domain.Astronomy
import eu.sesma.paraguas.api.model.AstronomyData
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class AstronomyMapper
@Inject
constructor() : Mapper<Astronomy, DataBlock?> {
    override fun map(input: DataBlock?): Astronomy {
        return Astronomy(
//                ageOfMoon = input.moonPhase?.ageOfMoon?.toInt() ?: 0,
//                sunrise = getTime(input.moonPhase?.sunrise?.hour ?: "00", input.moonPhase?.sunrise?.minute ?: "00"),
//                sunset = getTime(input.moonPhase?.sunset?.hour ?: "00", input.moonPhase?.sunset?.minute ?: "00")
        )
    }

//    private fun getTime(hour: String, minute: String): Date? {
//        val format = SimpleDateFormat("HH mm")
//        return format.parse("$hour $minute")
//    }
}