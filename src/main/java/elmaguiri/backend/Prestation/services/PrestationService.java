package elmaguiri.backend.Prestation.services;

import elmaguiri.backend.Prestation.entities.EtapePrestation;
import elmaguiri.backend.Prestation.entities.Prestation;
import elmaguiri.backend.Prestation.repository.EtapePrestationRepo;
import elmaguiri.backend.Prestation.repository.PrestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrestationService {
    @Autowired
    private PrestationRepository prestationRepository;
    @Autowired
    private EtapePrestationRepo prestationStepRepository;

    @Transactional
    public Prestation createPrestation(String name, String description, List<EtapePrestation> steps) {
        Prestation prestation = new Prestation();
        prestation.setName(name);
        prestation.setDescription(description);
        prestation.setEtapes(steps);
        for (EtapePrestation step : steps) {
            step.setPrestation(prestation);
        }
        return prestationRepository.save(prestation);
    }

    public List<Prestation> getAllPrestations() {
        return prestationRepository.findAll();
    }

    public Prestation getPrestationById(Long id) throws ResourceNotFoundException {
        return prestationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prestation not found with id " + id));
    }

    @Transactional
    public Prestation updatePrestation(Long id, String name, String description, List<EtapePrestation> steps) throws ResourceNotFoundException {
        Prestation prestation = prestationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prestation not found with id " + id));
        prestation.setName(name);
        prestation.setDescription(description);
        prestation.getEtapes().clear();
        prestation.getEtapes().addAll(steps);
        for (EtapePrestation step : steps) {
            step.setPrestation(prestation);
        }
        return prestationRepository.save(prestation);
    }

    @Transactional
    public void deletePrestation(Long id) throws ResourceNotFoundException {
        Prestation prestation = prestationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prestation not found with id " + id));
        prestationRepository.delete(prestation);
    }

    public List<EtapePrestation> getStepsByPrestationId(Long prestationId) {
        return prestationStepRepository.findByPrestationId(prestationId);
    }

    public Prestation createPrestation(Prestation prestation) {
        return prestationRepository.save(prestation);
    }
}
