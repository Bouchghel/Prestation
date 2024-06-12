package elmaguiri.backend.Prestation;

import elmaguiri.backend.Prestation.entities.EtapeOperation;
import elmaguiri.backend.Prestation.entities.EtapePrestation;
import elmaguiri.backend.Prestation.entities.Operation;
import elmaguiri.backend.Prestation.entities.Prestation;
import elmaguiri.backend.Prestation.repository.EtapeOperationRepo;
import elmaguiri.backend.Prestation.repository.OperationRepository;
import elmaguiri.backend.Prestation.repository.PrestationRepository;
import elmaguiri.backend.Prestation.services.OperationService;
import elmaguiri.backend.Prestation.services.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class PrestationApplication implements CommandLineRunner {

	@Autowired
	private PrestationRepository prestationRepository;

	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private OperationService operationService;
	@Autowired
	private EtapeOperationRepo etapeOperationRepo;

	public static void main(String[] args) {
		SpringApplication.run(PrestationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createPrestation();
		updateOperation();
	}

	private void createPrestation() throws ResourceNotFoundException {
		if (prestationRepository.findAll().isEmpty()) {
			// Create the prestation
			Prestation prestation = new Prestation();
			prestation.setName("Création d'une entreprise");
			prestation.setDescription("Service de création d'une nouvelle entreprise.");

			// Define the steps with their attributes
			EtapePrestation step1 = new EtapePrestation();
			step1.setName("Rédaction des statuts");
			Map<String, Object> attributesTemplate1 = new HashMap<>();
			attributesTemplate1.put("document", null);
			attributesTemplate1.put("dateRedaction", "");
			attributesTemplate1.put("author", "");
			step1.setAttributesTemplate(attributesTemplate1);
			step1.setPrestation(prestation);

			EtapePrestation step2 = new EtapePrestation();
			step2.setName("Dépôt au greffe");
			Map<String, Object> attributesTemplate2 = new HashMap<>();
			attributesTemplate2.put("document", null);
			attributesTemplate2.put("dateDepot", "");
			attributesTemplate2.put("confirmationNumber", "");
			attributesTemplate2.put("salaire", 0);
			step2.setAttributesTemplate(attributesTemplate2);
			step2.setPrestation(prestation);

			EtapePrestation step3 = new EtapePrestation();
			step3.setName("Publication de l'annonce légale");
			Map<String, Object> attributesTemplate3 = new HashMap<>();
			attributesTemplate3.put("document", null);
			attributesTemplate3.put("datePublication", "");
			attributesTemplate3.put("journal", "");
			attributesTemplate3.put("link", "");
			step3.setAttributesTemplate(attributesTemplate3);
			step3.setPrestation(prestation);

			// Add steps to the prestation
			prestation.setEtapes(List.of(step1, step2, step3));

			// Save the prestation
			prestationRepository.save(prestation);
		}

		// Create a new operation using the steps of the prestation
		List<Prestation> prestations = prestationRepository.findAll();
		Prestation prestation = prestations.get(0);
		operationService.createOperation(prestation.getId());

		// Update operation

	}
	private void updateOperation() throws ResourceNotFoundException {
		List<Operation> operations = operationRepository.findAll();

		// Parcourez chaque opération
		for (Operation operation : operations) {
			// Récupérez toutes les étapes de cette opération
			List<EtapeOperation> etapes = operation.getEtapeOperations();

			// Parcourez chaque étape pour mettre à jour l'attribut "author"
			for (EtapeOperation etape : etapes) {
				// Récupérez les attributs actuels de l'étape
				Map<String, Object> attributs = etape.getAttributes();

				// Modifiez la valeur de l'attribut "author"
				attributs.put("author", "kamal");
				attributs.put("salaire",15);

				// Mettez à jour les attributs de l'étape
				etape.setAttributes(attributs);

				// Enregistrez les modifications dans la base de données
				etapeOperationRepo.save(etape);
			}
		}

	}

}
