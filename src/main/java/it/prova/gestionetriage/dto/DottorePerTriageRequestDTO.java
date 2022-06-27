package it.prova.gestionetriage.dto;

public class DottorePerTriageRequestDTO {
	private Long id;
	private String nome;
	private String cognome;
	private String codiceDipendente;

	public DottorePerTriageRequestDTO() {
	}

	public DottorePerTriageRequestDTO(String nome, String cognome, String codiceDipendente) {
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
	}

	public DottorePerTriageRequestDTO(Long id, String nome, String cognome, String codiceDipendente) {
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
}
