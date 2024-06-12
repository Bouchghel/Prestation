package elmaguiri.backend.Prestation.controller;

import elmaguiri.backend.Prestation.entities.EtapeOperation;
import elmaguiri.backend.Prestation.entities.Operation;
import elmaguiri.backend.Prestation.services.OperationService;
import elmaguiri.backend.Prestation.services.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/operations")
public class OperationController {
    @Autowired
    private OperationService operationService;

    @PostMapping
    public ResponseEntity<Operation> createOperation(@RequestBody Operation request) throws ResourceNotFoundException {
        Operation operation = operationService.createOperation(request.getPrestation().getId());
        return new ResponseEntity<>(operation, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Operation>> getAllOperations() {
        List<Operation> operations = operationService.getAllOperations();
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operation> getOperationById(@PathVariable Long id) throws ResourceNotFoundException {
        Operation operation = operationService.getOperationById(id);
        return new ResponseEntity<>(operation, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Operation> updateOperationStatus(@PathVariable Long id, @RequestParam String status) throws ResourceNotFoundException {
        Operation updatedOperation = operationService.updateOperationStatus(id, status);
        return new ResponseEntity<>(updatedOperation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperation(@PathVariable Long id) throws ResourceNotFoundException {
        operationService.deleteOperation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{operationId}/steps")
    public ResponseEntity<List<EtapeOperation>> getStepsByOperationId(@PathVariable Long operationId) {
        List<EtapeOperation> steps = operationService.getStepsByOperationId(operationId);
        return new ResponseEntity<>(steps, HttpStatus.OK);
    }

    @PutMapping("/steps/{id}")
    public ResponseEntity<EtapeOperation> updateStep(@PathVariable Long id, @RequestBody EtapeOperation request) throws ResourceNotFoundException {
        EtapeOperation updatedStep = operationService.updateStep(id, request.getStatus(), request.getAttributes());
        return new ResponseEntity<>(updatedStep, HttpStatus.OK);
    }
}
