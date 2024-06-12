package elmaguiri.backend.Prestation.repository;

import elmaguiri.backend.Prestation.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}
