package eu.sesma.paraguas.domain.mappers

import eu.sesma.paraguas.api.model.DataPoint
import eu.sesma.paraguas.domain.Astronomy
import javax.inject.Inject

class AstronomyMapper
@Inject
constructor() : Mapper<Astronomy, DataPoint?> {
    override fun map(input: DataPoint?): Astronomy {
        return Astronomy(
            ageOfMoon = input?.moonPhase?.toInt() ?: 0,
            sunrise = input?.sunriseTime.toDate(),
            sunset = input?.sunsetTime.toDate()
        )
    }
}