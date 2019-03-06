package eu.sesma.paraguas.usecases

import android.location.Geocoder
import eu.sesma.paraguas.domain.City
import eu.sesma.paraguas.domain.mappers.CityMapper
import eu.sesma.paraguas.location.RxLocationProvider
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class CityUseCase
@Inject constructor(
    private val geocoder: Geocoder,
    private val locationProvider: RxLocationProvider,
    private val cityMapper: CityMapper
) {

    companion object {
        private val TIMEOUT = 5L
    }

    fun execute(): Observable<City> = locationProvider.getLocationObservable()
        .take(1)
        .timeout(TIMEOUT, TimeUnit.SECONDS)
        .map { location ->
            val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
            cityMapper.map(Pair(location, if (addresses.isNotEmpty()) addresses[0] else null))
        }
}