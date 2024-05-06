package model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numeroTessera;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String cognome;
	
	@Column(name = "data_nascita")
	private LocalDate dataDiNascita;
	
	@OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
	private Set<Prestito> listaPrestiti = new HashSet<Prestito>();
	
	
////

	public Utente() {
	
	}
////
	
	
	public int getNumeroTessera() {
		return numeroTessera;
	}

	public void setListaPrestiti(Set<Prestito> listaPrestiti) {
		this.listaPrestiti = listaPrestiti;
	}


	public Set<Prestito> getListaPrestiti() {
		return listaPrestiti;
	}


	public void setNumeroTessera(int numeroTessera) {
		this.numeroTessera = numeroTessera;
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

	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}


	@Override
	public String toString() {
		return "Utente [numeroTessera=" + numeroTessera + ", nome=" + nome + ", cognome=" + cognome
				+ ", dataDiNascita=" + dataDiNascita + "]";
	}
	
	
}
