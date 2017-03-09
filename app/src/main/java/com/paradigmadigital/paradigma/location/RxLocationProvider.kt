package com.paradigmadigital.paradigma.location

import android.content.Context
import android.os.Bundle
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.paradigmadigital.paradigma.api.model.GeoLookUp
import com.paradigmadigital.paradigma.usecases.GeoLookUpApiUseCase
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import javax.inject.Inject


class RxLocationProvider
@Inject
constructor(val context: Context, val useCase: GeoLookUpApiUseCase) {

    private var googleApiClient: GoogleApiClient? = null

    var observableEmitter: ObservableEmitter<GeoLookUp>? = null

    private val connectionCallback = object : GoogleApiClient.ConnectionCallbacks {
        override fun onConnected(bundle: Bundle?) {
            val lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient)
            if (lastLocation != null) {
                useCase.execute(lastLocation.latitude.toString(), lastLocation.longitude.toString())
                        .subscribe({ handleOnResult(it) }, { handleOnError(it) })
            }
        }

        override fun onConnectionSuspended(i: Int) {
            observableEmitter?.onError(Throwable("Connection error"))
        }
    }

    private val connectFailListener = object : GoogleApiClient.OnConnectionFailedListener {
        override fun onConnectionFailed(result: com.google.android.gms.common.ConnectionResult) {
            observableEmitter?.onError(Throwable(result.errorMessage))
        }
    }

    fun getGeoLookUpObservable(): Observable<GeoLookUp> {
        return Observable.create {
            observableEmitter = it
            buildGoogleApiClient()
        }
    }

    @Synchronized private fun buildGoogleApiClient() {
        if (googleApiClient == null) {
            googleApiClient = GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(connectionCallback)
                    .addOnConnectionFailedListener(connectFailListener)
                    .addApi(LocationServices.API)
                    .build()
        }

        googleApiClient?.connect()
    }

    private fun handleOnResult(geoLookUp: GeoLookUp) {
        observableEmitter?.onNext(geoLookUp)
        observableEmitter?.onComplete()
        googleApiClient?.disconnect()
    }

    private fun handleOnError(throwable: Throwable) {
        observableEmitter?.onError(throwable)
        googleApiClient?.disconnect()
    }
}
