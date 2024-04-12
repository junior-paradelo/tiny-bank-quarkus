package tiny.bank.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import tiny.bank.model.BankUser;

@ApplicationScoped
public class BankUserRepository implements PanacheRepository<BankUser> {
}
