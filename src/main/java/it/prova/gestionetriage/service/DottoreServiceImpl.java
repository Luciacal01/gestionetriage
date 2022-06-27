package it.prova.gestionetriage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionetriage.model.Dottore;
import it.prova.gestionetriage.repository.DottoreRepository;

@Service
public class DottoreServiceImpl implements DottoreService {
	
	@Autowired
	private DottoreRepository dottoreRepository;

	@Override
	public List<Dottore> listAllDottori() {
		return (List<Dottore>) dottoreRepository.findAll();
	}

	@Override
	public Dottore caricaSingoloPaziente(Long id) {
		return dottoreRepository.findById(id).orElse(null);
	}

	@Override
	public Dottore aggiorna(Dottore pazienteInstance) {
		return dottoreRepository.save(pazienteInstance);
	}

	@Override
	public Dottore inserisciNuovo(Dottore dottoreInstance) {
		return dottoreRepository.save(dottoreInstance);
	}

	@Override
	public void rimuovi(Dottore dottoreInstance) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Dottore> findByExample(Dottore example) {
		return dottoreRepository.findByExample(example);
	}

}
