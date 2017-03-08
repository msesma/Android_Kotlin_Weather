package com.paradigmadigital.paradigma.location

import android.content.Context
import android.os.Bundle
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.paradigmadigital.paradigma.api.model.GeoLookUp
import com.paradigmadigital.paradigma.usecases.GeoLookUpApiUseCase
import rx.Observable
import rx.Subscriber
import javax.inject.Inject


class LocationProvider
@Inject
constructor(val context: Context, val useCase: GeoLookUpApiUseCase) {

    private var googleApiClient: GoogleApiClient? = null

    var subscriber: Subscriber<GeoLookUp>? = null;

    private val connectionCallback = object : GoogleApiClient.ConnectionCallbacks {
        override fun onConnected(bundle: Bundle?) {
            val lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient)
            useCase.execute(lastLocation.latitude.toString(), lastLocation.longitude.toString())
                    .subscribe({ handleOnResult(it) }, { handleOnError(it) })
        }

        override fun onConnectionSuspended(i: Int) {

        }
    }

    private val connectFailListener = object : GoogleApiClient.OnConnectionFailedListener {
        override fun onConnectionFailed(result: com.google.android.gms.common.ConnectionResult) {
        }
    }

    fun getGeoLookUpObservable(): Observable<GeoLookUp> {
        return Observable.create(
                Observable.OnSubscribe<GeoLookUp>({ sub ->
                    this.subscriber = sub as Subscriber<GeoLookUp>?
                    buildGoogleApiClient()
                })
        )
    }


    @Synchronized private fun buildGoogleApiClient() {
        googleApiClient = GoogleApiClient.Builder(context)
                .addConnectionCallbacks(connectionCallback)
                .addOnConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener { })
                .addApi(LocationServices.API)
                .build()

        googleApiClient?.connect()
    }

    private fun handleOnResult(geoLookUp: GeoLookUp) {
        subscriber?.onNext(geoLookUp)
        subscriber?.onCompleted()
    }

    private fun handleOnError(throwable: Throwable) {
        subscriber?.onError(throwable)
    }
}
