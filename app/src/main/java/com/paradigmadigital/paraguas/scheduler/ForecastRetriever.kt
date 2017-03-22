package com.paradigmadigital.paraguas.scheduler

import android.util.Log
import com.paradigmadigital.paraguas.domain.Astronomy
import com.paradigmadigital.paraguas.domain.City
import com.paradigmadigital.paraguas.domain.CurrentWeather
import com.paradigmadigital.paraguas.domain.ForecastItem
import com.paradigmadigital.paraguas.domain.cache.CacheProvider
import com.paradigmadigital.paraguas.platform.PermissionManager
import com.paradigmadigital.paraguas.usecases.AstronomyApiUseCase
import com.paradigmadigital.paraguas.usecases.CityUseCase
import com.paradigmadigital.paraguas.usecases.ConditionsApiUseCase
import com.paradigmadigital.paraguas.usecases.ForecastApiUseCase
import com.paradigmadigital.paraguas.wear.WearUpdater
import javax.inject.Inject

class ForecastRetriever @Inject constructor(
        private val conditionsUseCase: ConditionsApiUseCase,
        private val astronomyUseCase: AstronomyApiUseCase,
        private val hourlyUseCase: ForecastApiUseCase,
        private val cityUseCase: CityUseCase,
        private val cache: CacheProvider,
        private val permissionManager: PermissionManager,
        private val wearUpdater: WearUpdater
) {

    private val TAG = ForecastRetriever::class.simpleName
//    private var jobService: JobService? = null
//    private var job: JobParameters? = null

    private var city: City? = null

    fun start() {

        city = cache.city
        if (city != null) {
            handleOnCityResult(city!!)
            return
        }

        if (permissionManager.hasLocationPremission) {
            cityUseCase.execute()
                    .subscribe(
                            { cache.city = it; this.handleOnCityResult(it) },
                            { handleOnError(it) }
                    )
        }
    }

    private fun handleOnCityResult(city: City) {
        Log.d(TAG, cache.city.toString())
        handleOnResult()

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

    private fun handleOnError(throwable: Throwable?) {
        Log.d(TAG, "Job finished FAIL: " + throwable.toString())
//        jobService?.jobFinished(job!!, true)
    }

    private fun handleOnResult(
            currentWeather: CurrentWeather? = cache.currentWeather,
            astronomy: Astronomy? = cache.astronomy,
            forecast: List<ForecastItem>? = cache.forecast) {
        if (currentWeather == null || astronomy == null || forecast == null) return

        wearUpdater.update(currentWeather, astronomy, forecast, city?.city ?: "")

        Log.d(TAG, "Job finished OK")
//        jobService?.jobFinished(job!!, false)
    }

}

