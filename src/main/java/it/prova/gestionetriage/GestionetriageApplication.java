package it.prova.gestionetriage;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionetriage.model.Dottore;
import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.model.StatoPaziente;
import it.prova.gestionetriage.service.DottoreService;
import it.prova.gestionetriage.service.PazienteService;

@SpringBootApplication
public class GestionetriageApplication implements CommandLineRunner{
	
	@Autowired
	private PazienteService pazienteService;
	
	@Autowired
	private DottoreService dottoreService;
	
	public static void main(String[] args) {
		SpringApplication.run(GestionetriageApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Dottore dottore1=new Dottore("Giovanni", "Cartello", "67G67");
		Dottore dottore2=new Dottore("Eros", "Fabri", "89D21");
		Dottore dottore3=new Dottore("Anna", "Marino", "01P64");
		
		dottoreService.inserisciNuovo(dottore1);
		dottoreService.inserisciNuovo(dottore2);
		dottoreService.inserisciNuovo(dottore3);
		
//		Paziente paziente1=new Paziente("Saverio", "Carelli", new Date(), "SVRCRL96D78W007Q", dottore1);
//		Paziente paziente2=new Paziente("Mario", "Rossi",new SimpleDateFormat("dd/MM/yyyy").parse("13/12/2001"), "MARROSS78P13H501F", dottore2);
//		Paziente paziente3= new Paziente("Antonio", "Marrone",new SimpleDateFormat("dd/MM/yyyy").parse("23/03/2021"), "ANTMAR88P13H501F",dottore3 );
//		
//		pazienteService.inserisciNuovo(paziente1);
//		pazienteService.inserisciNuovo(paziente2);
//		pazienteService.inserisciNuovo(paziente3);
	}
	
	

}
