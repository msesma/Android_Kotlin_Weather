package com.paradigmadigital.paraguas

import com.paradigmadigital.paraguas.domain.mappers.AstronomyMapper
import com.paradigmadigital.paraguas.usecases.AstronomyApiUseCase
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.text.SimpleDateFormat

class AstronomyApiUseCaseShould : MockWebServerTestBase() {

    lateinit private var useCase: AstronomyApiUseCase
    @Mock
    private lateinit var mapperMock: AstronomyMapper

    @Before
    @Throws(Exception::class)
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        useCase = AstronomyApiUseCase(httpClient, baseEndpoint, mapperMock)
    }

    @Test
    @Throws(Exception::class)
    fun getCityForCoordinatesHappyPath() {
        enqueueMockResponse(200, "astronomy_mock_response.json")
        val format = SimpleDateFormat("HH mm")

        useCase.execute("CA", "San Francisco")

                .subscribe({
                    assertThat(it?.ageOfMoon).isEqualTo(10)
                    assertThat(it?.sunrise).isEqualTo(format.parse("07 01"))
                    assertThat(it?.sunset).isEqualTo(format.parse("16 56"))
                })
    }

    @Test
    @Throws(Exception::class)
    fun getCityForCoordinatesUsesCorrectUrl() {
        enqueueMockResponse(200)

        useCase.execute("CA", "San Francisco").subscribe()

        assertGetRequestSentTo("/astronomy/q/CA/San Francisco.json")
    }
}

