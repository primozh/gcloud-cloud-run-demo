package si.hrovat.gcloud.run.demo

import io.quarkus.test.junit.NativeImageTest
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.junit.mockito.InjectSpy
import io.restassured.RestAssured
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import si.hrovat.gcloud.run.demo.ExampleService
import java.util.*

@QuarkusTest
@Tag("integration")
class ExampleResourceTest {

    @InjectSpy
    lateinit var exampleService: ExampleService

    @Test
    fun testHelloEndpoint() {
        When {
            get("/hello")
        } Then {
            statusCode(200)
            body(equalTo("Hello RESTEasy"))
        }
    }

    @Test
    fun testGreetingEndpoint() {
        val uuid = UUID.randomUUID().toString()
        Given {
            pathParam("name", uuid)
        } When {
            get("/hello/greeting/{name}")
        } Then {
            statusCode(200)
            body(equalTo("hello $uuid"))
        }

        Mockito.verify(exampleService).greeting(uuid)
    }
}

@NativeImageTest
class NativeExampleResourceIT : ExampleResourceTest() { // Execute the same tests but in native mode.
}