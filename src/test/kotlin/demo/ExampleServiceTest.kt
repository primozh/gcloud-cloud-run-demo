package demo

import io.smallrye.mutiny.helpers.test.UniAssertSubscriber
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import si.hrovat.gcloud.run.demo.ExampleService

internal class ExampleServiceTest {
    @Test
    fun testGreeting() {
        val service = ExampleService()
        assertThat(
            "Greeting is 'hello Primoz'",
            service.greeting("Primoz").subscribe().withSubscriber(UniAssertSubscriber.create()).item,
            equalTo("hello Primoz")
        )
    }
}