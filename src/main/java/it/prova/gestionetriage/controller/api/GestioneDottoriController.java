package it.prova.gestionetriage.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import it.prova.gestionetriage.controller.api.exception.DottoreNotFoundException;
import it.prova.gestionetriage.dto.DottoreDTO;
import it.prova.gestionetriage.dto.DottorePerTriageRequestDTO;
import it.prova.gestionetriage.dto.DottorePerTriageResponseDTO;
import it.prova.gestionetriage.model.Dottore;
import it.prova.gestionetriage.service.DottoreService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/gestioneDottori")
public class GestioneDottoriController {
	@Autowired 
	private DottoreService dottoreService;
	
	@Autowired
	private WebClient webClient;
	
	@GetMapping
	public List<DottoreDTO> getAll(){
		return DottoreDTO.createDottoreDTOListFromModelList(dottoreService.listAllDottori());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DottoreDTO createNew(@RequestBody DottoreDTO dottoreInput) {
		// ANDREBBE GESTITA CON ADVICE!!!
		// se mi viene inviato un id jpa lo interpreta come update ed a me (producer)
		// non sta bene
		if (dottoreInput.getId() != null)
			throw new RuntimeException("Non è ammesso fornire un id per la creazione");

		// prima di salvarlo devo verificare se la banca dati esterna lo censisce
		ResponseEntity<DottorePerTriageResponseDTO> response = webClient.post().uri("")
				.body(Mono.just(new DottorePerTriageRequestDTO(dottoreInput.getNome(), dottoreInput.getCognome(),
						dottoreInput.getCodiceDipendente())), DottorePerTriageRequestDTO.class)
				.retrieve().toEntity(DottorePerTriageResponseDTO.class).block();

		// ANDREBBE GESTITA CON ADVICE!!!
		if (response.getStatusCode() != HttpStatus.CREATED)
			throw new RuntimeException("Errore nella creazione della nuova voce tramite api esterna!!!");

		Dottore dottoreInserito = dottoreService.inserisciNuovo(dottoreInput.buildDottoreModel());
		return DottoreDTO.buildDottoreDTOFromModel(dottoreInserito);
	}
	
	@GetMapping("/{id}")
	public DottoreDTO findById(@PathVariable(value = "id", required = true) long id) {
		Dottore dottoreModel = dottoreService.caricaSingoloPaziente(id);

		// ora invoco il sistema esterno per capire se il dipendente ha una posizione
		// previdenziale
		// nel caso affermativo valorizzo apposito campo
		// il block significa agire in maniera sincrona, attendendo la risposta
		DottorePerTriageResponseDTO dottorePerTriageResponseDTO = webClient.get().uri("/" + dottoreModel.getCodiceDipendente())
				.retrieve().bodyToMono(DottorePerTriageResponseDTO.class).block();

		DottoreDTO result = DottoreDTO.buildDottoreDTOFromModel(dottoreModel);

		//se l'altra banca ha trovato qualcosa io riempio il DTO altrimenti no
		if (dottorePerTriageResponseDTO != null && dottorePerTriageResponseDTO.getCodiceDipendente() != null
				&& dottorePerTriageResponseDTO.getCodiceDipendente().equals(result.getCodiceDipendente())) {
			result.setInServizio(dottorePerTriageResponseDTO.getInServizio());
			result.setInVisita(dottorePerTriageResponseDTO.getInVisita());
		}

		return result;
	}
	
	@PostMapping("/search")
	public List<DottoreDTO> search(@RequestBody DottoreDTO example) {
		return DottoreDTO.createDottoreDTOListFromModelList(dottoreService.findByExample(example.buildDottoreModel()));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public DottoreDTO update(@Valid @RequestBody DottoreDTO dottoreInput, @PathVariable(required = true) Long id) {
		Dottore dipendente = dottoreService.caricaSingoloPaziente(id);

		if (dipendente == null)
			throw new DottoreNotFoundException("Dipendente not found con id: " + id);
		
		ResponseEntity<DottorePerTriageResponseDTO> response = webClient.post().uri("")
				.body(Mono.just(new DottorePerTriageRequestDTO(dottoreInput.getNome(), dottoreInput.getCognome(),
						dottoreInput.getCodiceDipendente())), DottorePerTriageRequestDTO.class)
				.retrieve().toEntity(DottorePerTriageResponseDTO.class).block();
		
		if (response.getStatusCode() == HttpStatus.OK	)
			throw new RuntimeException("Errore nella modifica della voce tramite api esterna!!!");
		
		Dottore dottoreInserito = dottoreService.aggiorna(dottoreInput.buildDottoreModel());
		return DottoreDTO.buildDottoreDTOFromModel(dottoreInserito);
	}

}
