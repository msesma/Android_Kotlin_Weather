package com.paradigmadigital.paradigma

import android.os.Bundle
import android.util.Log
import com.paradigmadigital.paradigma.api.model.WeatherData
import com.paradigmadigital.paradigma.platform.BaseActivity
import javax.inject.Inject


class SplashActivity : BaseActivity() {

    @Inject
    lateinit var useCase: CurrentWeatherUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getApplicationComponent().inject(this)

        useCase.execute("Islamabad").subscribe({ this.handleOnResult(it) }, { this.handleOnError(it) })
    }

    private fun handleOnResult(weatherData: WeatherData?) {
        Log.d("Current Weather", weatherData?.weather?.get(0)?.description)
    }

    private fun handleOnError(throwable: Throwable) {
        throwable.let {
            Log.e("Current Weather", it.toString())
        }
    }
}
