package tiny.bank.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import tiny.bank.model.Account;
import tiny.bank.repository.AccountRepository;

import java.util.List;

@Path("/account")
public class AccountResource {

    @Inject
    private AccountRepository accountRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public List<Account> getAccounts() {
        return accountRepository.listAll();
    }

    @GET
    @Path("/count")
    public Long countAccounts() {
        return accountRepository.count();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/insert")
    public Account insertAccount(Account insertedAccount) {
        accountRepository.persist(insertedAccount);
        return insertedAccount;
    }
}
