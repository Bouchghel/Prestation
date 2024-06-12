package elmaguiri.backend.Prestation.controller;

import elmaguiri.backend.Prestation.entities.EtapePrestation;
import elmaguiri.backend.Prestation.entities.Prestation;
import elmaguiri.backend.Prestation.services.PrestationService;
import elmaguiri.backend.Prestation.services.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestations")
public class PrestationController {
    @Autowired
    private PrestationService prestationService;

    @PostMapping
    public ResponseEntity<Prestation> createPrestation(@RequestBody Prestation prestation) {
        Prestation createdPrestation = prestationService.createPrestation(prestation.getName(), prestation.getDescription(), prestation.getEtapes());
        return new ResponseEntity<>(createdPrestation, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Prestation>> getAllPrestations() {
        List<Prestation> prestations = prestationService.getAllPrestations();
        return new ResponseEntity<>(prestations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestation> getPrestationById(@PathVariable Long id) throws ResourceNotFoundException {
        Prestation prestation = prestationService.getPrestationById(id);
        return new ResponseEntity<>(prestation, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestation> updatePrestation(@PathVariable Long id, @RequestBody Prestation prestation) throws ResourceNotFoundException {
        Prestation updatedPrestation = prestationService.updatePrestation(id, prestation.getName(), prestation.getDescription(), prestation.getEtapes());
        return new ResponseEntity<>(updatedPrestation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestation(@PathVariable Long id) throws ResourceNotFoundException {
        prestationService.deletePrestation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{prestationId}/steps")
    public ResponseEntity<List<EtapePrestation>> getStepsByPrestationId(@PathVariable Long prestationId) {
        List<EtapePrestation> steps = prestationService.getStepsByPrestationId(prestationId);
        return new ResponseEntity<>(steps, HttpStatus.OK);
    }
}

