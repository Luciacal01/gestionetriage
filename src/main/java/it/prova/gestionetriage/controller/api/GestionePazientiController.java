package it.prova.gestionetriage.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import it.prova.gestionetriage.dto.PazienteDTO;
import it.prova.gestionetriage.service.PazienteService;

@RestController
@RequestMapping("/api/paziente")
public class GestionePazientiController {
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private PazienteService pazienteService;
	
	@GetMapping
	public List<PazienteDTO> getAll(){
		return PazienteDTO.createPazienteDTOListFromModelList(pazienteService.listAllPazienti());
	}
}
