package eu.sesma.paraguas.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject


class RxLocationProvider
@Inject
constructor(val context: Context) {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private val subject = BehaviorSubject.create<Location>()

    fun getLocationObservable(): Observable<Location> {
        getLocation()
        return subject
    }

    @SuppressLint("MissingPermission")
    @Synchronized
    private fun getLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let { subject.onNext(location) }
        }
    }
}
