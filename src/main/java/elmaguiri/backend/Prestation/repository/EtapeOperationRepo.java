package elmaguiri.backend.Prestation.repository;

import elmaguiri.backend.Prestation.entities.EtapeOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtapeOperationRepo extends JpaRepository<EtapeOperation, Long> {
    List<EtapeOperation> findByOperationId(Long operationId);
}
