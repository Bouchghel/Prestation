package elmaguiri.backend.Prestation.services;

import elmaguiri.backend.Prestation.entities.EtapeOperation;
import elmaguiri.backend.Prestation.entities.EtapePrestation;
import elmaguiri.backend.Prestation.entities.Operation;
import elmaguiri.backend.Prestation.entities.Prestation;
import elmaguiri.backend.Prestation.repository.EtapeOperationRepo;
import elmaguiri.backend.Prestation.repository.OperationRepository;
import elmaguiri.backend.Prestation.repository.PrestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

///


@Service
public class OperationService {
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private EtapeOperationRepo stepRepository;
    @Autowired
    private PrestationRepository prestationRepository;

    @Transactional
    public Operation createOperation(Long prestationId) throws ResourceNotFoundException {
        Prestation prestation = prestationRepository.findById(prestationId)
                .orElseThrow(() -> new ResourceNotFoundException("Prestation not found with id " + prestationId));

        List<EtapePrestation> etapesPrestation = prestation.getEtapes();
        List<EtapeOperation> etapesOperation = new ArrayList<>();

        // Créer les étapes d'opération en copiant les étapes de prestation
        for (EtapePrestation etapePrestation : etapesPrestation) {
            EtapeOperation etapeOperation = new EtapeOperation();
            etapeOperation.setName(etapePrestation.getName());
            etapeOperation.setAttributes(etapePrestation.getAttributesTemplate());
            etapesOperation.add(etapeOperation);
        }

        // Créer et sauvegarder l'opération avec les étapes
        Operation operation = new Operation();
        operation.setPrestation(prestation);
        operation.setStatus("In Progress");
        operation.setEtapeOperations(etapesOperation);
        operation = operationRepository.save(operation);

        // Assurer que chaque étape d'opération est associée à cette opération
        for (EtapeOperation etape : etapesOperation) {
            etape.setOperation(operation);
        }
        stepRepository.saveAll(etapesOperation);

        return operation;
    }

    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    public Operation getOperationById(Long id) throws ResourceNotFoundException {
        return operationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Operation not found with id " + id));
    }

    @Transactional
    public Operation updateOperationStatus(Long id, String status) throws ResourceNotFoundException {
        Operation operation = operationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Operation not found with id " + id));
        operation.setStatus(status);
        return operationRepository.save(operation);
    }

    @Transactional
    public void deleteOperation(Long id) throws ResourceNotFoundException {
        Operation operation = operationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Operation not found with id " + id));
        operationRepository.delete(operation);
    }

    public List<EtapeOperation> getStepsByOperationId(Long operationId) {
        return stepRepository.findByOperationId(operationId);
    }

    @Transactional
    public EtapeOperation updateStep(Long id, String status, Map<String, Object> attributes) throws ResourceNotFoundException {
        EtapeOperation step = stepRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Step not found with id " + id));
        step.setStatus(status);
        step.setAttributes(attributes);
        return stepRepository.save(step);
    }
}