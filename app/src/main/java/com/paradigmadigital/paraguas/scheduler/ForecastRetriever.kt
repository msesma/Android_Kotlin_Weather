package com.paradigmadigital.paraguas.scheduler

import android.Manifest
import android.content.Context
import android.util.Log
import com.paradigmadigital.paraguas.domain.Astronomy
import com.paradigmadigital.paraguas.domain.City
import com.paradigmadigital.paraguas.domain.CurrentWeather
import com.paradigmadigital.paraguas.domain.ForecastItem
import com.paradigmadigital.paraguas.domain.cache.CacheProvider
import com.paradigmadigital.paraguas.log.DiskLogger
import com.paradigmadigital.paraguas.usecases.AstronomyApiUseCase
import com.paradigmadigital.paraguas.usecases.CityUseCase
import com.paradigmadigital.paraguas.usecases.ConditionsApiUseCase
import com.paradigmadigital.paraguas.usecases.ForecastApiUseCase
import com.paradigmadigital.paraguas.wear.WearUpdater
import io.reactivex.Observable
import io.reactivex.subjects.CompletableSubject
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject

class ForecastRetriever
@Inject
constructor(
        private val conditionsUseCase: ConditionsApiUseCase,
        private val astronomyUseCase: AstronomyApiUseCase,
        private val hourlyUseCase: ForecastApiUseCase,
        private val cityUseCase: CityUseCase,
        private val cache: CacheProvider,
        private val context: Context,
        private val wearUpdater: WearUpdater,
        private val diskLogger: DiskLogger
) {

    companion object {
        private val TAG = ForecastRetriever::class.simpleName
    }

    private val completable: CompletableSubject = CompletableSubject.create()

    fun getForecast(): Observable<Void> {

        if (cache.city != null) {
            handleOnCityResult(cache.city)
            return completable.toObservable<Void>()
        }

        if (EasyPermissions.hasPermissions(context, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            cityUseCase.execute()
                    .subscribe(
                            { cache.city = it; this.handleOnCityResult(it) },
                            { handleOnError(it) }
                    )
        }
        return completable.toObservable<Void>()
    }

    private fun handleOnCityResult(city: City?) {
        if (city == null) {
            completable.onError(Exception("city is null"))
            return
        }

        Log.d(TAG, cache.city.toString())
        handleOnResult(city = city)

        if (cache.currentWeather == null) conditionsUseCase.execute(country = city.countryCode, city = city.city)
                .subscribe(
                        { cache.currentWeather = it; handleOnResult(currentWeather = it) },
                        { handleOnError(it) }
                )

        if (cache.astronomy == null) astronomyUseCase.execute(country = city.countryCode, city = city.city)
                .subscribe(
                        { cache.astronomy = it; handleOnResult(astronomy = it) },
                        { handleOnError(it) }
                )

        if (cache.forecast == null) hourlyUseCase.execute(country = city.countryCode, city = city.city)
                .subscribe(
                        { cache.forecast = it; handleOnResult(forecast = it) },
                        { handleOnError(it) }
                )
    }

    private fun handleOnError(throwable: Throwable) {
        Log.d(TAG, "Job finished FAIL: " + throwable.toString())
        diskLogger.log(TAG, "Job finished FAIL: " + throwable.toString())
        completable.onError(throwable)
    }

    private fun handleOnResult(
            currentWeather: CurrentWeather? = cache.currentWeather,
            astronomy: Astronomy? = cache.astronomy,
            forecast: List<ForecastItem>? = cache.forecast,
            city: City? = cache.city) {
        if (currentWeather == null || astronomy == null || forecast == null) return
        wearUpdater.update(currentWeather, astronomy, forecast, city?.city ?: "")
        diskLogger.log(TAG, "Job finished OK in: " + city?.city)
        Log.d(TAG, "Job finished OK")
        completable.onComplete()
    }
}

