package it.prova.gestionetriage.service;

import java.util.List;

import it.prova.gestionetriage.model.Dottore;

public interface DottoreService {
	public List<Dottore> listAllDottori();

	public Dottore caricaSingoloPaziente(Long id);

	public Dottore aggiorna(Dottore pazienteInstance);

	public Dottore inserisciNuovo(Dottore dottoreInstance);

	public void rimuovi(Dottore dottoreInstance);
	
	public List<Dottore> findByExample(Dottore example);
}
