package si.hrovat.gcloud.run.demo

import com.google.cloud.firestore.Firestore
import io.quarkus.test.Mock
import io.quarkus.test.junit.QuarkusTest
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import si.hrovat.gcloud.run.demo.ExampleService
import javax.inject.Inject

internal class ExampleServiceTest {

    companion object {
        lateinit var firestore: Firestore

        @BeforeAll
        @JvmStatic
        fun before() {
            firestore = Mockito.mock(Firestore::class.java)
        }
    }

    @Test
    fun testGreeting() {
        val service = ExampleService(firestore)
        assertThat(
            "Greeting is 'hello Primoz'",
            service.greeting("Primoz").subscribe().withSubscriber(UniAssertSubscriber.create()).item,
            equalTo("hello Primoz")
        )
    }
}