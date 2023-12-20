package ma.enset.event_driven_architecture.query.repositories;
import ma.enset.event_driven_architecture.query.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {

}
