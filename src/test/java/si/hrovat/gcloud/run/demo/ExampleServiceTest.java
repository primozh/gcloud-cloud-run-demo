package si.hrovat.gcloud.run.demo;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ExampleServiceTest {

    @Test
    public void testGreeting() {
        var service = new ExampleService();
        assertThat("Greeting is 'hello Primoz'", service.greeting("Primoz"), equalTo("hello Primoz"));
    }
}