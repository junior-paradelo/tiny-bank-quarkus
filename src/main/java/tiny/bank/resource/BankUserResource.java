package tiny.bank.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tiny.bank.model.BankUser;
import tiny.bank.repository.BankUserRepository;

import java.net.URI;

@Path("/user")
public class BankUserResource {

    @Inject
    private BankUserRepository bankUserRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getUsers() {
        return Response.ok(bankUserRepository.findAll()).build();
    }

    @GET
    @Path("/get/{id}")
    public Response getUser(@PathParam("id") Long userId) {
        return bankUserRepository.findByIdOptional(userId).map(user -> Response.ok(user).build()).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countUsers() {
        return Response.ok(bankUserRepository.count()).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/insert")
    public Response insertUser(BankUser insertedUser) {
        bankUserRepository.persist(insertedUser);
        if (bankUserRepository.isPersistent(insertedUser)) {
            return Response.created(URI.create("/user/" + insertedUser.getId())).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Transactional
    @Path("/delete/{id}")
    public Response deleteAccount(@PathParam("id") Long userId) {
        boolean result = bankUserRepository.deleteById(userId);
        return result ? Response.noContent().build() : Response.status(Response.Status.BAD_REQUEST).build();
    }
}
