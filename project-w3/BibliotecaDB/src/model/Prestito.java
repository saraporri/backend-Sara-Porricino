package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "ricercaTessera", query = "SELECT p FROM Prestito p WHERE p.restituzioneEffettiva IS NULL AND p.id = :id")
@NamedQuery(name = "ricercaPrestitiScaduti", query = "SELECT p FROM Prestito p WHERE p.restituzioneEffettiva IS NULL AND p.restituzionePrevista < CURRENT_DATE")
public class Prestito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_elemento_prestato")
	private ElementoCatalogo elemento_prestato;
	
	@Column(nullable = false)
	private LocalDate dataInizioPrestito;
	
	@Column(nullable = false)
	private LocalDate restituzionePrevista;
	
	private LocalDate restituzioneEffettiva;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utente_tessera")
	private Utente utente;
	
////
	
	public Prestito() {

	}
////
	

	public ElementoCatalogo getElementoPrestato() {
		return elemento_prestato;
	}

	public Utente getUtente() {
		return utente;
	}


	public void setUtente(Utente utente) {
		this.utente = utente;
	}


	public void setElementoPrestato(ElementoCatalogo elemento_prestato) {
		this.elemento_prestato = elemento_prestato;
	}

	public LocalDate getDataInizioPrestito() {
		return dataInizioPrestito;
	}

	public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
		this.dataInizioPrestito = dataInizioPrestito;
	}

	public LocalDate getRestituzionePrevista() {
		return restituzionePrevista;
	}

	public void setRestituzionePrevista(LocalDate restituzionePrevista) {
		this.restituzionePrevista = restituzionePrevista;
	}

	public LocalDate getRestituzioneEffettiva() {
		return restituzioneEffettiva;
	}

	public void setRestituzioneEffettiva(LocalDate restituzioneEffettiva) {
		this.restituzioneEffettiva = restituzioneEffettiva;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Prestito [id=" + id + ", elementoPrestato=" + elemento_prestato
				+ ", dataInizioPrestito=" + dataInizioPrestito + ", restituzionePrevista=" + restituzionePrevista
				+ ", restituzioneEffettiva=" + restituzioneEffettiva + "]";
	}
	
	
	
}
