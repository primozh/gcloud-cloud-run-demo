package si.hrovat.gcloud.run.demo;

import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ExampleServiceTest {

    @Test
    void testGreeting() {
        var service = new ExampleService();
        assertThat("Greeting is 'hello Primoz'",
                service.greeting("Primoz").subscribe().withSubscriber(UniAssertSubscriber.create()).getItem(),
                equalTo("hello Primoz"));
    }
}