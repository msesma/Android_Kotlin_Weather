package com.paradigmadigital.paradigma

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.paradigmadigital.paradigma.api.model.WeatherData


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val useCase = CurrentWeatherUseCase()
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
