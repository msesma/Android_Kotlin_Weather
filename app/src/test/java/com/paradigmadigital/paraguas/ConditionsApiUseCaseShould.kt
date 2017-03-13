package com.paradigmadigital.paraguas

import com.paradigmadigital.paraguas.api.model.CurrentWeather
import com.paradigmadigital.paraguas.domain.mappers.CurrentWeatherMapper
import com.paradigmadigital.paraguas.usecases.ConditionsApiUseCase
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.HttpException

class ConditionsApiUseCaseShould : MockWebServerTestBase() {

    lateinit private var useCase: ConditionsApiUseCase
    @Mock
    private lateinit var mapperMock: CurrentWeatherMapper

    @Before
    @Throws(Exception::class)
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        useCase = ConditionsApiUseCase(httpClient, baseEndpoint, CurrentWeatherMapper())
    }

    @Test
    @Throws(Exception::class)
    fun getCityForCoordinatesHappyPath() {
        enqueueMockResponse(200, "conditions_mock_response.json")
        val observer = TestObserver<CurrentWeather>()
        val currentWeather = CurrentWeather(0f, "http://icons-ak.wxug.com/i/c/k/partlycloudy.gif", 19.1f, 19.1f, "Partly Cloudy")

        useCase.execute("CA", "San Francisco").subscribe(observer)
        observer.await()

        observer.assertNoErrors()
                .assertValue(currentWeather)
    }

    @Test
    @Throws(Exception::class)
    fun getCityForCoordinatesUsesCorrectUrl() {
        enqueueMockResponse(200, "conditions_mock_response.json")

        useCase.execute("CA", "San Francisco").subscribe()

        assertGetRequestSentTo("/conditions/q/CA/San%20Francisco.json")
    }

    @Test
    @Throws(Exception::class)
    fun getCityForCoordinatesManagerHttpError() {
        enqueueMockResponse(500, "conditions_mock_response.json")
        val observer = TestObserver<CurrentWeather>()

        useCase.execute("CA", "San Francisco").subscribe(observer)
        observer.await()

        observer.assertError { it -> (it as HttpException).code() == 500 }
    }
}
