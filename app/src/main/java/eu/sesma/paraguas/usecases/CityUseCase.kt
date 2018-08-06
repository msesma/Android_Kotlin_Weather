package eu.sesma.paraguas.usecases

import eu.sesma.paraguas.domain.City
import eu.sesma.paraguas.domain.mappers.CityMapper
import eu.sesma.paraguas.location.RxLocationProvider
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class CityUseCase
@Inject
constructor(val locationProvider: RxLocationProvider, val cityMapper: CityMapper) {

    private val TIMEOUT = 5L

    fun execute(): Observable<City> = locationProvider.getGeoLookUpObservable()
            .take(1)
            .timeout(TIMEOUT, TimeUnit.SECONDS)
            .map { geoLookUp -> cityMapper.map(geoLookUp) }

}