package it.prova.gestionetriage.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestionetriage.model.Dottore;
import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.model.StatoPaziente;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PazienteDTO {
	private Long id;
	private String nome;
	private String cognome;
	private Date dataRegistrazione;
	private String codiceFiscale;
	private StatoPaziente statoPaziente;
	@JsonIgnoreProperties(value="pazienteAttualmenteInVisita")
	private DottoreDTO dottore;
	
	public PazienteDTO() {
		super();
	}

	public PazienteDTO(String nome, String cognome, Date dataRegistrazione, String codiceFiscale,
			StatoPaziente statoPaziente) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataRegistrazione = dataRegistrazione;
		this.codiceFiscale = codiceFiscale;
		this.statoPaziente = statoPaziente;
	}

	public PazienteDTO(Long id, String nome, String cognome, Date dataRegistrazione, String codiceFiscale,
			StatoPaziente statoPaziente, DottoreDTO dottore) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataRegistrazione = dataRegistrazione;
		this.codiceFiscale = codiceFiscale;
		this.statoPaziente = statoPaziente;
		this.dottore = dottore;
	}

	public PazienteDTO(Long id, String nome, String cognome, Date dataRegistrazione, String codiceFiscale,
			StatoPaziente statoPaziente) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataRegistrazione = dataRegistrazione;
		this.codiceFiscale = codiceFiscale;
		this.statoPaziente = statoPaziente;
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

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public StatoPaziente getStatoPaziente() {
		return statoPaziente;
	}

	public void setStatoPaziente(StatoPaziente statoPaziente) {
		this.statoPaziente = statoPaziente;
	}

	public DottoreDTO getDottore() {
		return dottore;
	}

	public void setDottore(DottoreDTO dottore) {
		this.dottore = dottore;
	}
	
	public Paziente buildPazienteModel() {
		Paziente result = new Paziente(this.id, this.nome, this.cognome,this.dataRegistrazione ,this.codiceFiscale, this.statoPaziente, this.dottore.buildDottoreModel());
		if(this.dottore!=null) {
			result.setDottore(this.dottore.buildDottoreModel()); 
		}
		return result;
	}

	public static PazienteDTO buildPazienteDTOFromModel(Paziente pazienteModel) {
		PazienteDTO result = new PazienteDTO(pazienteModel.getId(), pazienteModel.getNome(), pazienteModel.getCognome(),
				pazienteModel.getDataRegistrazione(), pazienteModel.getCodiceFiscale(), pazienteModel.getStatoPaziente());
		if(pazienteModel.getDottore()!= null) {
			result.setDottore(DottoreDTO.buildDottoreDTOFromModel(pazienteModel.getDottore()));
		}

		return result;
	}
	


	public static List<PazienteDTO> createPazienteDTOListFromModelList(List<Paziente> modelListInput) {
		return modelListInput.stream().map(pazienteEntity -> {
			return PazienteDTO.buildPazienteDTOFromModel(pazienteEntity);
		}).collect(Collectors.toList());
	}
}
