package elmaguiri.backend.Prestation.repository;

import elmaguiri.backend.Prestation.entities.EtapePrestation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtapePrestationRepo extends JpaRepository<EtapePrestation, Long> {
    List<EtapePrestation> findByPrestationId(Long prestationId);
}
