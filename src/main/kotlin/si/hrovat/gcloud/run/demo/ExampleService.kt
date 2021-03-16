package si.hrovat.gcloud.run.demo

import io.smallrye.mutiny.Uni
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/hello")
class ExampleResource @Inject constructor (val service: ExampleService) {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    fun greeting(@PathParam("name") name: String): Uni<Response> =
        service.greeting(name).map { Response.ok(it).build() }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(): Uni<Response> {
        return Uni.createFrom().item(Response.ok("Hello RESTEasy").build())
    }
}

@ApplicationScoped
class ExampleService {
    fun greeting(name: String): Uni<String> = Uni.createFrom().item("hello $name")
}