package si.hrovat.gcloud.run.demo;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;

@QuarkusTest
@Tag("integration")
public class ExampleResourceTest {

    @InjectSpy
    ExampleService exampleService;

    @Test
    void testHelloEndpoint() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello RESTEasy"));
    }

    @Test
    void testGreetingEndpoint() {
        var uuid = UUID.randomUUID().toString();
        given()
            .pathParam("name", uuid)
            .when().get("/hello/greeting/{name}")
        .then()
            .statusCode(200)
            .body(is("hello " + uuid));

        verify(exampleService).greeting(uuid);
    }

}