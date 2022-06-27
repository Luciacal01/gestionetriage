package it.prova.gestionetriage.service;

import java.util.List;

import it.prova.gestionetriage.model.Paziente;

public interface PazienteService {
	public List<Paziente> listAllPazienti();

	public Paziente caricaSingoloPaziente(Long id);

	public Paziente aggiorna(Paziente pazienteInstance);

	public Paziente inserisciNuovo(Paziente pazienteInstance);

	public void rimuovi(Paziente pazienteInstance);
	
	public List<Paziente> findByExample(Paziente example);
}
