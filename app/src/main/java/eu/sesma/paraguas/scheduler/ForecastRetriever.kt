package eu.sesma.paraguas.scheduler

import android.Manifest
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import eu.sesma.paraguas.domain.City
import eu.sesma.paraguas.domain.WeatherData
import eu.sesma.paraguas.domain.cache.CacheProvider
import eu.sesma.paraguas.log.DiskLogger
import eu.sesma.paraguas.ui.master.Graph
import eu.sesma.paraguas.usecases.CityUseCase
import eu.sesma.paraguas.usecases.ForecastApiUseCase
import eu.sesma.paraguas.wear.WearUpdater
import eu.sesma.paraguas.widget.ParaguasAppWidgetProvider
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.CompletableSubject
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject


class ForecastRetriever
@Inject
constructor(
    private val forecastUseCase: ForecastApiUseCase,
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
    private val compositeDisposable = CompositeDisposable()

    fun getForecast(): Completable {
        if (cache.city != null) {
            handleOnCityResult(cache.city)
            return completable
        }

        if (EasyPermissions.hasPermissions(context, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            compositeDisposable.add(cityUseCase.execute()
                .subscribe(
                    { cache.city = it; this.handleOnCityResult(it) },
                    { handleOnError(it) }
                )
            )
        }
        return completable
    }

    private fun handleOnCityResult(city: City?) {
        if (city == null) {
            completable.onError(Exception("city is null"))
            return
        }

        Log.d(TAG, cache.city.toString())

        compositeDisposable.add(forecastUseCase.execute(city)
            .subscribe(
                { handleOnResult(it) },
                { handleOnError(it) }
            )
        )
    }

    private fun handleOnError(throwable: Throwable) {
        Log.d(TAG, "Job finished FAIL: " + throwable.toString())
        diskLogger.log(TAG, "Job finished FAIL: " + throwable.toString())
        completable.onError(throwable)
    }

    private fun handleOnResult(data: WeatherData) {
        val (city, currentWeather, astronomy, forecast) = data
        if (currentWeather == null || astronomy == null || forecast == null) return
        wearUpdater.update(currentWeather, astronomy, forecast, city?.city ?: "")
        updateWidget(data)
        diskLogger.log(TAG, "Job finished OK in: " + city?.city)
        Log.d(TAG, "Job finished OK")
        completable.onComplete()
    }

    private fun updateWidget(data: WeatherData) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val remoteViews = RemoteViews(context.packageName, eu.sesma.paraguas.R.layout.appwidget)

        val bitmap = Graph(context).apply {
            val (_, currentWeather, astronomy, forecast) = data
            this.currentWeather = currentWeather
            this.astronomy = astronomy
            this.forecast = forecast ?: emptyList()
        }.drawWidget()

        remoteViews.setImageViewBitmap(eu.sesma.paraguas.R.id.widget_graph, bitmap)
        val ids = AppWidgetManager.getInstance(context)
            .getAppWidgetIds(ComponentName(context, ParaguasAppWidgetProvider::class.java))
        ids.forEach { appWidgetManager.updateAppWidget(it, remoteViews) }
    }
}

