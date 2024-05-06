package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Prestito;
import utils.JpaUtil;

public class PrestitoDAO {
	
	static Logger log = LoggerFactory.getLogger(ElementoCatalogoDAO.class);
	static EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
	
//SALVA
	
	public void save(Prestito p) {
		
		try {
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			log.info("Prestito: " + p.toString());
			
		} catch (Exception ex) {
			em.getTransaction().rollback();
			log.error(ex.getMessage());
			
		} finally {
			em.close();
		}

	}
	
//TROVA DA ID
	
	public Prestito getById(long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			return em.find(Prestito.class, id);

		} finally {
			em.close();
		}

	}
	
//ELIMINA DA ID
	
	public void deleteById(long id) {
	   try {
		Prestito trovato = em.find(Prestito.class, id);
		em.remove(trovato);
		em.getTransaction().commit();
		log.info("Eliminato " + trovato);
	   } finally {
		em.close();
	  }
   }

//RICERCA DA TESSERA
	
	public void ricercaByTessera(int numeroTessera) {
		
		 Query query = em.createNamedQuery("ricercaTessera");
			query.setParameter("utente_id", numeroTessera);
			List<Prestito> prestiti = query.getResultList();
			System.out.println("Articoli non restituiti da utente con tessera: " + numeroTessera);
			for(Prestito e : prestiti) {
				System.out.println(e);
			}
	}

//RICERCA SCADUTI 
	
	public void ricercaScaduti() {
		
		Query query = em.createNamedQuery("ricercaPrestitiScaduti");
		List<Prestito> prestiti = query.getResultList();
		System.out.println("Articoli non restituiti in tempo: ");
		for(Prestito e : prestiti) {
			System.out.println(e);
		}
	}
}
