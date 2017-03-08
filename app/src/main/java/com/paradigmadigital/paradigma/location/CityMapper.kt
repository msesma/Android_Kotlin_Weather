package com.paradigmadigital.paradigma.location

import com.paradigmadigital.paradigma.api.model.GeoLookUp
import com.paradigmadigital.paradigma.domain.City
import com.paradigmadigital.paradigma.domain.Mapper
import javax.inject.Inject

class CityMapper
@Inject
constructor(): Mapper<City, GeoLookUp> {
    override fun map(input: GeoLookUp): City {
        return City(
                city = input.location?.city ?: "",
                countryCode = input.location?.country ?: ""
        )
    }
}