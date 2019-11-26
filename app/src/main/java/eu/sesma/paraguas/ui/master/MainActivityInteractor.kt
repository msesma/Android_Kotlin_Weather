package eu.sesma.paraguas.ui.master

import android.util.Log
import eu.sesma.paraguas.domain.City
import eu.sesma.paraguas.domain.ForecastItem
import eu.sesma.paraguas.domain.WeatherData
import eu.sesma.paraguas.domain.cache.CacheProvider
import eu.sesma.paraguas.navigation.Navigator
import eu.sesma.paraguas.platform.PermissionManager
import eu.sesma.paraguas.scheduler.Scheduler
import eu.sesma.paraguas.usecases.CityUseCase
import eu.sesma.paraguas.usecases.ForecastApiUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivityInteractor
@Inject
constructor(
    private val forecastUseCase: ForecastApiUseCase,
    private val cityUseCase: CityUseCase,
    private val cache: CacheProvider,
    private val permissionManager: PermissionManager,
    private val navigator: Navigator,
    private val scheduler: Scheduler
) {

    companion object {
        private val TAG = MainActivityInteractor::class.simpleName
    }

    private var subscriber: RefreshSubscriber? = null
    private val compositeDisposable = CompositeDisposable()

    val city get() = cache.city?.city

    fun initialize(subscriber: RefreshSubscriber) {
        this.subscriber = subscriber
        scheduler.start()
    }

    fun refresh() {
        val city = cache.city
        if (city != null) {
            handleOnCityResult(city)
            return
        }

        if (permissionManager.locationPremission) {
            compositeDisposable.add(cityUseCase.execute()
                .subscribe(
                    { cache.city = it; this.handleOnCityResult(it) },
                    { subscriber?.onError(Exception(it)) }
                )
            )
        }
    }

    private fun handleOnCityResult(city: City) {
        Log.d(TAG, cache.city.toString())

        compositeDisposable.add(forecastUseCase.execute(city)
            .subscribe(
                { subscriber?.handleOnForecastResult(it) },
                { subscriber?.onError(Exception(it)) }
            )
        )
    }

    fun navigateToDetail(forecastItem: ForecastItem) {
        navigator.navigateToDetail(forecastItem)
    }

    fun dispose() {
        compositeDisposable.dispose()
    }

    interface RefreshSubscriber {
        fun onError(ex: Exception)

        fun handleOnForecastResult(data: WeatherData)
    }

}
