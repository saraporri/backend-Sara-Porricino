package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Utente;
import utils.JpaUtil;

public class UtenteDAO {

	private static final Logger logger = LoggerFactory.getLogger(UtenteDAO.class);
	static EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

//SALVA 
	
	public void save(Utente u) {
		
		try {

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			em.persist(u);

			transaction.commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();

			logger.error("Error saving Utente: " + u.getClass().getSimpleName(), ex);
			throw ex;

		} finally {
			em.close();
		}

	}
	
//AGGIORNA
	
	public void refresh(Utente u) {
		
		try {

			em.refresh(u);

		} finally {
			em.close();
		}

	}

//TROVA DA Numero Tessera
	
	public Utente getByNumTessera(int id) {
	
		try {

			return em.find(Utente.class, id);

		} finally {
			em.close();
		}

	}

//ELIMINA
	
	public void delete(Utente u) {

		try {

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			em.remove(u);

			transaction.commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			logger.error("Error deleting Utente: " + u.getClass().getSimpleName(), ex);
			throw ex;

		} finally {
			em.close();
		}

	}
}
