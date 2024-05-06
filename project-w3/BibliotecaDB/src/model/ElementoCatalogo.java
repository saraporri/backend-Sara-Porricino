package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity 
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "elemento_type", discriminatorType = DiscriminatorType.STRING)
@NamedQuery(name = "ricercaAnno", query = "SELECT e FROM ElementoCatalogo e WHERE e.annoPubblicazione = :annoPubblicazione")
@NamedQuery(name = "ricercaTitolo", query = "SELECT e FROM ElementoCatalogo e WHERE LOWER(e.titolo) LIKE LOWER(:titolo)")
public abstract class ElementoCatalogo {

	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "isbn_seq" )
	@SequenceGenerator( name = "isbn_seq", sequenceName = "isbn_seq" )
	private long codiceIsbn;
	
	@Column(nullable = false)
	private String titolo;
	
	@Column(nullable = false)
	private int annoPubblicazione;
	
	@Column(nullable = false)
	private int nPagine;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "elemento_prestato")
	private List<Prestito> prestiti;
	

//////
	
	public ElementoCatalogo() {
	
	}
//////
	
	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public int getAnnoPubblicazione() {
		return annoPubblicazione;
	}

	public void setAnnoPubblicazione(int annoPubblicazione) {
		this.annoPubblicazione = annoPubblicazione;
	}

	public int getnPagine() {
		return nPagine;
	}

	public void setnPagine(int nPagine) {
		this.nPagine = nPagine;
	}

	public long getCodiceIsbn() {
		return codiceIsbn;
	}

	@Override
	public String toString() {
		return "ElementoCatalogo [codiceIsbn=" + this.codiceIsbn + ", titolo=" + this.titolo + ", annoPubblicazione="
				+ this.annoPubblicazione + ", nPagine=" + this.nPagine + "]";
	}

	
	
	
}
