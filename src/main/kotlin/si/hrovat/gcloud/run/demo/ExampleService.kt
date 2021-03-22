package si.hrovat.gcloud.run.demo

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.FirestoreOptions
import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.infrastructure.Infrastructure
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/hello")
class ExampleResource @Inject constructor(val service: ExampleService) {

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

@Path("/accounts")
class AccountResource @Inject constructor(val service: ExampleService) {

    @POST
    fun storeAccount(account: Account): Uni<Response> =
        service.storeAccount(account)
            .map { Response.status(Response.Status.CREATED).entity(it).build() }
}

class FirestoreProducer {

    @ConfigProperty(name = "gcloud.project-id")
    lateinit var projectId: String

    @javax.enterprise.inject.Produces
    @ApplicationScoped
    fun firestore(): Firestore {
        val secret = GoogleCredentials.getApplicationDefault()
        val options =
            FirestoreOptions.getDefaultInstance().toBuilder()
                .setCredentials(secret)
                .setProjectId(projectId)
                .build();

        return options.service
    }
}

@ApplicationScoped
class ExampleService @Inject constructor(val firestore: Firestore) {
    val logger: Logger = Logger.getLogger(ExampleService::class.java.name)

    fun greeting(name: String): Uni<String> = Uni.createFrom().item("hello $name")

    fun storeAccount(account: Account): Uni<Account> {
        val collection = firestore.collection("accounts")

        val set = collection.document().set(account)
        return Uni.createFrom().item(set.get())
            .invoke { item -> logger.info("Updated at ${item.updateTime}") }
            .map { account }
            .runSubscriptionOn(Infrastructure.getDefaultExecutor())
    }
}

data class Account(val username: String, val user: User?) {
    constructor() : this("", null)
}

data class User(val name: String?, val surname: String?) {
    constructor() : this(null, null)
}