package eu.sesma.paraguas.usecases

import eu.sesma.paraguas.api.Endpoint
import eu.sesma.paraguas.api.services.WeatherService
import eu.sesma.paraguas.domain.mappers.CurrentWeatherMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class ConditionsApiUseCase
@Inject
constructor(client: OkHttpClient, endpoint: Endpoint, val mapper: CurrentWeatherMapper) {

    val service: WeatherService

    init {
        service = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(endpoint.url)
                .build()
                .create(WeatherService::class.java)
    }

    fun execute(country: String, city: String) = service.getConditions(country, city)
            .map { mapper.map(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}