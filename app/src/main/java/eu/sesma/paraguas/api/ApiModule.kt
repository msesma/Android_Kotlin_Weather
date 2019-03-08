package eu.sesma.paraguas.api

import android.content.Context
import android.util.Log
import dagger.Module
import dagger.Provides
import eu.sesma.paraguas.BuildConfig
import eu.sesma.paraguas.api.services.WeatherService
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class ApiModule() {

    companion object {
        private const val CACHE_MAX_AGE = 3600 // Seconds
        private const val TIMEOUT = 15L // Seconds
        private const val CACHE_SIZE_BYTES = (1024 * 1024 * 5).toLong() // 5 MB
    }

    @Provides
    fun provideCacheControlHeader(): CacheControl = CacheControl.Builder().maxAge(CACHE_MAX_AGE, TimeUnit.SECONDS)
        .build()

    @Provides
    fun provideWeatherService(client: OkHttpClient): WeatherService {
        return Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Endpoint.url)
            .build()
            .create(WeatherService::class.java)
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d("Retrofit", message) })
        interceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(context: Context, loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .cache(okhttp3.Cache(context.filesDir, CACHE_SIZE_BYTES))
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()
}
