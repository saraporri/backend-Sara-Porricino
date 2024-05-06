package model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity 
@DiscriminatorValue("Libro")
@NamedQuery(name = "ricercaAutore", query = "SELECT l FROM Libro l WHERE l.autore = :autore")
public class Libro extends ElementoCatalogo {
	
	@Column(nullable = false)
	private String autore;
	
	@Column(nullable = false)
	private String genere;
	
	
	public Libro() {
		super();
		
	}
	

	public String getAutore() {
		return autore;
	}



	public void setAutore(String autore) {
		this.autore = autore;
	}



	public String getGenere() {
		return genere;
	}



	public void setGenere(String genere) {
		this.genere = genere;
	}



	@Override
	public String toString() {
		return "Libro [autore=" + this.autore + ", genere=" + this.genere + ", isbn=" + this.getCodiceIsbn() + ", titolo=" + this.getTitolo()
				+ ", annoPubblicazione=" + this.getAnnoPubblicazione() + ", numeroPagine=" + this.getnPagine() + "]";
	}
	
	
}
