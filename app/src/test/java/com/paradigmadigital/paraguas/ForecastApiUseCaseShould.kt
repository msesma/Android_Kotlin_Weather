package com.paradigmadigital.paraguas

import com.paradigmadigital.paraguas.domain.mappers.ForecastMapper
import com.paradigmadigital.paraguas.usecases.ForecastApiUseCase
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.text.SimpleDateFormat

class ForecastApiUseCaseShould : MockWebServerTestBase() {

    lateinit private var useCase: ForecastApiUseCase
    @Mock
    private lateinit var mapperMock: ForecastMapper

    @Before
    @Throws(Exception::class)
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        useCase = ForecastApiUseCase(httpClient, baseEndpoint, mapperMock)
    }

    @Test
    @Throws(Exception::class)
    fun getCityForCoordinatesHappyPath() {
        enqueueMockResponse(200, "hourly_mock_response.json")
        val format = SimpleDateFormat("EEE mm")

        useCase.execute("CA", "San Francisco")

                .subscribe({
                    assertThat(it?.get(0)?.condition).isEqualTo("Clear")
                    assertThat(it?.get(0)?.feelslike).isEqualTo(19)
                    assertThat(it?.get(0)?.humidity).isEqualTo(65)
                    assertThat(it?.get(0)?.rainProbability).isEqualTo(0)
                    assertThat(it?.get(0)?.rainQuantity).isEqualTo(0)
                    assertThat(it?.get(0)?.snow).isEqualTo(0)
                    assertThat(it?.get(0)?.temp).isEqualTo(19)
                    assertThat(it?.get(0)?.windSpeed).isEqualTo(8)
                    assertThat(it?.get(0)?.iconUrl).isEqualTo("http://icons-ak.wxug.com/i/c/k/clear.gif")
                })
    }

    @Test
    @Throws(Exception::class)
    fun getCityForCoordinatesUsesCorrectUrl() {
        enqueueMockResponse(200)

        useCase.execute("CA", "San Francisco").subscribe()

        assertGetRequestSentTo("/hourly/q/CA/San Francisco.json")
    }
}
