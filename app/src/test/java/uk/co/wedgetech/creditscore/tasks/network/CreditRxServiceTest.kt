package uk.co.wedgetech.creditscore.tasks.network

import io.reactivex.Single
import junit.framework.Assert
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Buffer
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import uk.co.wedgetech.creditscore.domain.network.CreditValues
import java.io.IOException
import io.reactivex.observers.TestObserver
import org.hamcrest.CoreMatchers.hasItem
import retrofit2.HttpException
import uk.co.wedgetech.creditscore.domain.network.CreditReportInfo


class CreditRxServiceTest {

    lateinit internal var server: MockWebServer

    @Before
    @Throws(Exception::class)
    fun setUp() {
        server = MockWebServer()
        server.start()
        CreditRxService.BASE_URL = server.url("/").toString()
        CreditRxService.WeakHttpClient = true
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        server.shutdown()
    }


    @Test
    fun getCreditSuccess() {
        enqueueJson("get_credit_success.json")
        val singleCreditValues : Single<CreditValues> = CreditRxService.instance.getCredit()

        // given:
        val observer = TestObserver<CreditValues>()

        // when:
        singleCreditValues.subscribe(observer)

        // then:
        observer.assertComplete()
        observer.assertNoErrors()
        observer.assertValueCount(1)
        assertThat(observer.values(), hasItem(CreditValues(CreditReportInfo(123,700,0))))
    }

    @Test
    fun getCreditFail500() {
        server.enqueue(MockResponse().setResponseCode(500))
        val singleCreditValues : Single<CreditValues> = CreditRxService.instance.getCredit()

        // given:
        val observer = TestObserver<CreditValues>()

        // when:
        singleCreditValues.subscribe(observer)

        // then:
        observer.assertError(HttpException::class.java)
        observer.assertNotComplete()
    }


    internal fun enqueueJson(filename: String) {
        val json = javaClass.classLoader.getResourceAsStream(filename)
        try {
            val response = MockResponse().setBody(Buffer().readFrom(json))
            server.enqueue(response)
        } catch (e: IOException) {
            Assert.fail(e.toString())
        }

    }

}