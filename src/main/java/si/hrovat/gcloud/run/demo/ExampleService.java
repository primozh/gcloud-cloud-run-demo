package si.hrovat.gcloud.run.demo;

import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExampleService {
    public Uni<String> greeting(String name) {
        return Uni.createFrom().item("hello " + name);
    }
}
