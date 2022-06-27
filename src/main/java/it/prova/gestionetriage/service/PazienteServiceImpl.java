package it.prova.gestionetriage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.model.StatoPaziente;
import it.prova.gestionetriage.repository.PazienteRepository;

@Service
public class PazienteServiceImpl implements PazienteService {
	
	@Autowired
	private PazienteRepository pazienteRepository;

	@Override
	public List<Paziente> listAllPazienti() {
		return (List<Paziente>) pazienteRepository.findAll();
	}

	@Override
	public Paziente caricaSingoloPaziente(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paziente aggiorna(Paziente pazienteInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paziente inserisciNuovo(Paziente pazienteInstance) {
		pazienteInstance.setStatoPaziente(StatoPaziente.IN_ATTESA_VISITA);
		return pazienteRepository.save(pazienteInstance);
	}

	@Override
	public void rimuovi(Paziente pazienteInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Paziente> findByExample(Paziente example) {
		// TODO Auto-generated method stub
		return null;
	}

}
