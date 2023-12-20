package ma.enset.event_driven_architecture.query.repositories;

import ma.enset.event_driven_architecture.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

}
