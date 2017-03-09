package com.paradigmadigital.paradigma.usecases

import com.paradigmadigital.paradigma.domain.City
import com.paradigmadigital.paradigma.location.CityMapper
import com.paradigmadigital.paradigma.location.RxLocationProvider
import io.reactivex.Observable
import javax.inject.Inject


class CityUseCase
@Inject
constructor(val locationProvider: RxLocationProvider, val cityMapper: CityMapper) {

    fun execute(): Observable<City> {
        return locationProvider.getGeoLookUpObservable()
                .map { geoLookUp -> cityMapper.map(geoLookUp) }
    }

}