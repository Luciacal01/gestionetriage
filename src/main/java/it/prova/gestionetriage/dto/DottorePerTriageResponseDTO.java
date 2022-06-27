package it.prova.gestionetriage.dto;

public class DottorePerTriageResponseDTO {
	private String nome;
	private String cognome;
	private String codiceDipendente;
	private Boolean inServizio;
	private Boolean inVisita;
	
	public DottorePerTriageResponseDTO() {
		super();
	}

	public DottorePerTriageResponseDTO(String nome, String cognome, String codiceDipendente, Boolean inServizio,
			Boolean inVisita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
		this.inServizio = inServizio;
		this.inVisita = inVisita;
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

	public Boolean getInServizio() {
		return inServizio;
	}

	public void setInServizio(Boolean inServizio) {
		this.inServizio = inServizio;
	}

	public Boolean getInVisita() {
		return inVisita;
	}

	public void setInVisita(Boolean inVisita) {
		this.inVisita = inVisita;
	}
	
	
	
	

}
