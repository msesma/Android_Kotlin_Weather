package eu.sesma.paraguas.domain.mappers

import eu.sesma.paraguas.api.model.GeoLookUp
import eu.sesma.paraguas.domain.City
import javax.inject.Inject

class CityMapper
@Inject
constructor() : Mapper<City, GeoLookUp> {
    override fun map(input: GeoLookUp): City {
        return City(
                city = input.location?.city ?: "",
                countryCode = input.location?.country ?: ""
        )
    }
}