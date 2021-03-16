package si.hrovat.gcloud.run.demo;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExampleService {
    public String greeting(String name) {
        return "hello " + name;
    }
}
