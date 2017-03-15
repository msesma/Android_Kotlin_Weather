package com.paradigmadigital.paraguas.usecases

import com.paradigmadigital.paraguas.domain.City
import com.paradigmadigital.paraguas.domain.mappers.CityMapper
import com.paradigmadigital.paraguas.location.RxLocationProvider
import io.reactivex.Observable
import javax.inject.Inject


class CityUseCase
@Inject
constructor(val locationProvider: RxLocationProvider, val cityMapper: CityMapper) {

    fun execute(): Observable<City> = locationProvider.getGeoLookUpObservable()
                .map { geoLookUp -> cityMapper.map(geoLookUp) }

}