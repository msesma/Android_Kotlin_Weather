package com.paradigmadigital.paraguas.location

import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.jakewharton.rxrelay2.PublishRelay
import com.paradigmadigital.paraguas.api.model.GeoLookUp
import com.paradigmadigital.paraguas.usecases.GeoLookUpApiUseCase
import io.reactivex.Observable
import javax.inject.Inject


class RxLocationProvider
@Inject
constructor(val context: Context, val useCase: GeoLookUpApiUseCase) {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private val relay: PublishRelay<GeoLookUp> = PublishRelay.create()

    fun getGeoLookUpObservable(): Observable<GeoLookUp> {
        getLocation()
        return relay
    }

    @Synchronized private fun getLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                useCase.execute(location.latitude.toString(), location.longitude.toString())
                        .subscribe( { s -> relay.accept(s) })
            }
        }
    }
}
