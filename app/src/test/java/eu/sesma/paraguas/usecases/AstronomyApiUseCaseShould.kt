package eu.sesma.paraguas.usecases

import eu.sesma.paraguas.MockWebServerTestBase
import eu.sesma.paraguas.domain.Astronomy
import eu.sesma.paraguas.domain.mappers.AstronomyMapper
import eu.sesma.paraguas.usecases.AstronomyApiUseCase
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import java.text.SimpleDateFormat

class AstronomyApiUseCaseShould : MockWebServerTestBase() {

    lateinit private var useCase: AstronomyApiUseCase

    @Before
    @Throws(Exception::class)
    override fun setUp() {
        super.setUp()
        useCase = AstronomyApiUseCase(httpClient, baseEndpoint, AstronomyMapper())
    }

    @Test
    @Throws(Exception::class)
    fun getAstronomyHappyPath() {
        enqueueMockResponse(200, "astronomy_mock_response.json")
        val format = SimpleDateFormat("HH mm")
        val observer = TestObserver<Astronomy>()
        val astronomy = Astronomy(10, format.parse("07 01"), format.parse("16 56"))

        useCase.execute("CA", "San Francisco").subscribe(observer)
        observer.await()

        observer.assertNoErrors()
                .assertValue(astronomy)
    }

    @Test
    @Throws(Exception::class)
    fun getAstronomyUsesCorrectUrl() {
        enqueueMockResponse(200, "astronomy_mock_response.json")

        useCase.execute("CA", "San Francisco").subscribe()

        assertGetRequestSentTo("/astronomy/q/CA/San%20Francisco.json")
    }

    @Test
    @Throws(Exception::class)
    fun getAstronomyManagerHttpError() {
        enqueueMockResponse(500, "astronomy_mock_response.json")
        val observer = TestObserver<Astronomy>()

        useCase.execute("CA", "San Francisco").subscribe(observer)
        observer.await()

        observer.assertError { it -> (it as HttpException).code() == 500 }
    }
}

