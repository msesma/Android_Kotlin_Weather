package com.paradigmadigital.paraguas

import com.paradigmadigital.paraguas.usecases.GeoLookUpApiUseCase
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class GeoLookUpApiUseCaseShould : MockWebServerTestBase() {

    lateinit private var usecase: GeoLookUpApiUseCase

    @Before
    @Throws(Exception::class)
    override fun setUp() {
        super.setUp()
        usecase = GeoLookUpApiUseCase(httpClient, baseEndpoint)
    }

    @Test
    @Throws(Exception::class)
    fun getCityForCoordinatesHappyPath() {
        enqueueMockResponse(200, "geolookup_mock_response.json")

        usecase.execute("37.776289", "-122.395234")

                .subscribe({
            assertThat(it?.location?.city).isEqualTo("San Francisco")
            assertThat(it?.location?.country).isEqualTo("US")
        })
    }
}
