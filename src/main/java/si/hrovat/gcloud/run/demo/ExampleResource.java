package si.hrovat.gcloud.run.demo;

import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class ExampleResource {

    private final ExampleService service;

    @Inject
    public ExampleResource(ExampleService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public Uni<Response> greeting(@PathParam("name") String name) {
        return service.greeting(name).map(s -> Response.ok().entity(s).build());
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<Response> hello() {
        return Uni.createFrom().item(Response.ok("Hello RESTEasy").build());
    }


}