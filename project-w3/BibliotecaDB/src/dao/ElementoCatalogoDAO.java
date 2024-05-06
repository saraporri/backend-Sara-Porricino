package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.ElementoCatalogo;
import model.Libro;
import utils.JpaUtil;

public class ElementoCatalogoDAO extends JpaUtil {

	static Logger log = LoggerFactory.getLogger(ElementoCatalogoDAO.class);
	static EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
	
//SALVA
	
	public void save(ElementoCatalogo el) {
		
		try {
			em.getTransaction().begin();
			em.persist(el);
			em.getTransaction().commit();
			
		} catch (Exception ex) {
			em.getTransaction().rollback();
			log.error(ex.getMessage());
			
		} finally {
			em.close();
		}

	}
	
//ELIMINA 	
	
	public void delete(ElementoCatalogo el) {
   
		try {
			
			em.getTransaction().begin();
			em.remove(el);
			em.getTransaction().commit();
			
			log.info("Elemento eliminato correttamente!");
		} catch (Exception ec) {
			
			em.getTransaction().rollback();
			log.error(ec.getMessage());
		} finally {
			
			em.close();
		}
		
	}
	
//ELIMINA DA ISBN
	
	public void deleteByISBN(long codiceIsbn) {
		
		ElementoCatalogo e = em.find(ElementoCatalogo.class, codiceIsbn);
		if (e == null) {
			System.out.println("L'elemento con il codice ISBN " + codiceIsbn + " non è stato trovato!");
			return;
		}

		em.getTransaction().begin();
		em.remove(e);
		em.getTransaction().commit();

		System.out.println("L'elemento con il codice ISBN " + codiceIsbn + " è stato eliminato!");
	}
	
//CERCA PER ISBN	
	
	public ElementoCatalogo ricercaByISBN(long codiceIsbn) {

		try {
			em.getTransaction().begin();
			ElementoCatalogo cercato = em.find(ElementoCatalogo.class, codiceIsbn);
			 log.info(cercato.toString());
			 return cercato;
		} finally {
			em.close();
		}
	}
	
//CERCA PER ANNO	
	
	public void ricercaByAnno(int anno) {

		try {
			Query query = em.createNamedQuery("ricercaAnno");
			query.setParameter("annoPubblicazione", anno);
			List<ElementoCatalogo> trovati = query.getResultList();
			if(trovati.size()>0) {
			System.out.println("Carico elementi con l'anno: " + anno);
			
			for (ElementoCatalogo e : trovati) {
				System.out.println(e);
			   }
			}else {
				System.out.println("Nessun elemento trovato!");
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
//CERCA PER AUTORE
	
	public void ricercaByAutore(String autore) {

		try {
			Query query = em.createNamedQuery("ricercaAutore");
			query.setParameter("autore", autore);
			List<Libro> libri = query.getResultList();
			if(libri.size()>0) {
			System.out.println("Libri con autore: " + autore);
			for (ElementoCatalogo e : libri) {
				System.out.println(e);
			   }
			}else {
				System.out.println("Nessun elemento trovato!");
			}
		} catch (Exception e) {
			System.out.println("Nessun libro trovato!");
		}

	}
	
//CERCA PER TITOLO
	
	public void ricercaParzialeTitolo(String titolo) {

		try {
			Query query = em.createNamedQuery("ricercaTitolo");
			query.setParameter("titolo", "%" + titolo + "%");
			List<ElementoCatalogo> elemento = query.getResultList();
			System.out.println("Carico elementi con il titolo: " + titolo);
			for (ElementoCatalogo e : elemento) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println("Nessun elemento trovato!");
		}

	}
}
