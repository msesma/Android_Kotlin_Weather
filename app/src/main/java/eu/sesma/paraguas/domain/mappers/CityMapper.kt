package eu.sesma.paraguas.domain.mappers

import android.location.Address
import android.location.Location
import eu.sesma.paraguas.domain.City
import javax.inject.Inject

class CityMapper
@Inject
constructor() : Mapper<City, Pair<Location, Address?>> {
    override fun map(input: Pair<Location, Address?>): City {
        val (location, address) = input
        return City(
            city = address?.locality ?: "",
            countryCode = address?.countryCode ?: "",
            location = location
        )
    }
}