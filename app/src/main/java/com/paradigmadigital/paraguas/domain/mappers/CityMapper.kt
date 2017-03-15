package com.paradigmadigital.paraguas.domain.mappers

import com.paradigmadigital.paraguas.api.model.GeoLookUp
import com.paradigmadigital.paraguas.domain.City
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