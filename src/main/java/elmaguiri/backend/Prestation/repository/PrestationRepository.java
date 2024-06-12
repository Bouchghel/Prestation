package elmaguiri.backend.Prestation.repository;

import elmaguiri.backend.Prestation.entities.Prestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestationRepository extends JpaRepository<Prestation, Long> {
}
