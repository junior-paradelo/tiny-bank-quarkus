package tiny.bank.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import tiny.bank.model.Account;

@ApplicationScoped
public class AccountRepository implements PanacheRepository<Account> {

}
