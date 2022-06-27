package it.prova.gestionetriage.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestionetriage.model.Dottore;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DottoreDTO {
	private Long id;
	private String nome;
	private String cognome;
	private String codiceDipendente;
	@JsonIgnoreProperties(value="dottore")
	private PazienteDTO pazienteAttualmenteInVisita;
	
	private boolean inServizio;
	private boolean inVisita;
	public DottoreDTO() {
		super();
	}
	public DottoreDTO(Long id, String nome, String cognome, String codiceDipendente,
			PazienteDTO pazienteAttualmenteInVisita) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
		this.pazienteAttualmenteInVisita = pazienteAttualmenteInVisita;
	}
	public DottoreDTO(String nome, String cognome, String codiceDipendente) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
	}
	public DottoreDTO(String nome, String cognome, String codiceDipendente, PazienteDTO pazienteAttualmenteInVisita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
		this.pazienteAttualmenteInVisita = pazienteAttualmenteInVisita;
	}
	
	public DottoreDTO(Long id, String nome, String cognome, String codiceDipendente) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCodiceDipendente() {
		return codiceDipendente;
	}
	public void setCodiceDipendente(String codiceDipendente) {
		this.codiceDipendente = codiceDipendente;
	}
	public PazienteDTO getPazienteAttualmenteInVisita() {
		return pazienteAttualmenteInVisita;
	}
	public void setPazienteAttualmenteInVisita(PazienteDTO pazienteAttualmenteInVisita) {
		this.pazienteAttualmenteInVisita = pazienteAttualmenteInVisita;
	}
	
	
	
	public boolean isInServizio() {
		return inServizio;
	}
	public void setInServizio(boolean inServizio) {
		this.inServizio = inServizio;
	}
	public boolean isInVisita() {
		return inVisita;
	}
	public void setInVisita(boolean inVisita) {
		this.inVisita = inVisita;
	}
	public Dottore buildDottoreModel() {
		Dottore dottore= new Dottore(this.id, this.nome, this.cognome, this.codiceDipendente);
		if(this.pazienteAttualmenteInVisita!=null) {
			dottore.setPazienteAttualmenteInVisita(this.pazienteAttualmenteInVisita.buildPazienteModel());
		}
		return dottore;
	}

	public static DottoreDTO buildDottoreDTOFromModel(Dottore dottoreModel) {
		DottoreDTO dottoreDTO= new DottoreDTO(dottoreModel.getId(), dottoreModel.getNome(), dottoreModel.getCognome(),
				dottoreModel.getCodiceDipendente());
		if(dottoreModel.getPazienteAttualmenteInVisita()!= null) {
			dottoreDTO.setPazienteAttualmenteInVisita(PazienteDTO.buildPazienteDTOFromModel(dottoreModel.getPazienteAttualmenteInVisita()));
		}
		return dottoreDTO;
	}

	public static List<DottoreDTO> createDottoreDTOListFromModelList(List<Dottore> modelListInput) {
		return modelListInput.stream().map(dottoreEntity -> {
			return DottoreDTO.buildDottoreDTOFromModel(dottoreEntity);
		}).collect(Collectors.toList());
	}

}
