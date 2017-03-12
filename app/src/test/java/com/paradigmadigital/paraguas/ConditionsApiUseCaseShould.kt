package com.paradigmadigital.paraguas

import com.paradigmadigital.paraguas.domain.mappers.CurrentWeatherMapper
import com.paradigmadigital.paraguas.usecases.ConditionsApiUseCase
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ConditionsApiUseCaseShould : MockWebServerTestBase() {

    lateinit private var useCase: ConditionsApiUseCase
    @Mock
    private lateinit var mapperMock: CurrentWeatherMapper

    @Before
    @Throws(Exception::class)
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        useCase = ConditionsApiUseCase(httpClient, baseEndpoint, mapperMock)
    }

    @Test
    @Throws(Exception::class)
    fun getCityForCoordinatesHappyPath() {
        enqueueMockResponse(200, "conditions_mock_response.json")

        useCase.execute("CA", "San Francisco")

                .subscribe({
                    assertThat(it?.condition).isEqualTo("Partly Cloudy")
                    assertThat(it?.iconUrl).isEqualTo("http://icons-ak.wxug.com/i/c/k/partlycloudy.gif")
                    assertThat(it?.precip1hrMetric).isEqualTo(0f)
                    assertThat(it?.feelsLike).isEqualTo(0f)
                    assertThat(it?.temp).isEqualTo(0f)
                })
    }

    @Test
    @Throws(Exception::class)
    fun getCityForCoordinatesUsesCorrectUrl() {
        enqueueMockResponse(200)

        useCase.execute("CA", "San Francisco").subscribe()

        assertGetRequestSentTo("/conditions/q/CA/San Francisco.json")
    }
}
