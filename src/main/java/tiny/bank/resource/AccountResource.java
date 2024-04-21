package tiny.bank.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tiny.bank.model.Account;
import tiny.bank.repository.AccountRepository;

import java.net.URI;

@Path("/account")
public class AccountResource {

    @Inject
    private AccountRepository accountRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getAccounts() {
        return Response.ok(accountRepository.listAll()).build();
    }

    @GET
    @Path("/get/{id}")
    public Response getAccount(@PathParam("id") Long accountId) {
        return accountRepository.findByIdOptional(accountId).map(account -> Response.ok(account).build()).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countAccounts() {
        return Response.ok(accountRepository.count()).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/insert")
    public Response insertAccount(Account insertedAccount) {
        accountRepository.persist(insertedAccount);
        if (accountRepository.isPersistent(insertedAccount)) {
            return Response.created(URI.create("/account/" + insertedAccount.getId())).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Transactional
    @Path("/delete/{id}")
    public Response deleteAccount(@PathParam("id") Long accountId) {
        boolean result = accountRepository.deleteById(accountId);
        return result ? Response.noContent().build() : Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Transactional
    @Path("/add-balance/{id}")
    public Response addBalance(@PathParam("id") Long accountId, @QueryParam("balance") Double balance) {
        Account account = accountRepository.findById(accountId);
        account.setBalance(account.getBalance() + balance);
        return Response.ok().build();
    }

    @PUT
    @Transactional
    @Path("/subtract-balance/{id}")
    public Response subtractBalance(@PathParam("id") Long accountId, @QueryParam("balance") Double balance) {
        Account account = accountRepository.findById(accountId);
        account.setBalance(account.getBalance() - balance);
        return Response.ok().build();
    }
}
