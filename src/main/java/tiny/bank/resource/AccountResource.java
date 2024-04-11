package tiny.bank.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import tiny.bank.model.Account;
import tiny.bank.repository.AccountRepository;

@Path("/account")
public class AccountResource {

	@Inject
	private AccountRepository accountRepository;
	
	@GET
	public List<Account> index() {
		return accountRepository.listAll();
	}
	
	@POST
	@Transactional
	public Account insert(Account insertedAccount) {
		accountRepository.persist(insertedAccount);
		return insertedAccount;
	}
}
