package controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import dao.ElementoCatalogoDAO;
import dao.PrestitoDAO;
//import dao.PrestitoDAO;
import dao.UtenteDAO;
import model.ElementoCatalogo;
import model.Libro;
import model.Periodicita;
import model.Prestito;
import model.Rivista;
import model.Utente;

public class MainArchivio {

	static ElementoCatalogoDAO elementoDAO = new ElementoCatalogoDAO();
	static UtenteDAO utDAO = new UtenteDAO();
	static PrestitoDAO prDAO = new PrestitoDAO();
	static SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
	
	static Set<Prestito> pp = new HashSet<Prestito>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//SALVA
		//Libro libro = saveLibro();
		//Rivista rivista = saveRivista();
		//Utente utente = saveUtente();
		//Prestito prestito = savePrestito(utente, libro);
		
		//LEGGI
		//getByISBN(150);
		//GetByAutore("Roberto Bolaño");
		//GetByAutore("pippo");
		//searchAnno(2022);
		//searchAnno(20); 
		//searchTitoloParziale("ma"); case-insensitive
		
		//ELIMINA
		//delete(libro); tutto
	    //deleteViaISBN(300); tramite isbn
		
		/*Utente u2 = UtenteByNumTess(4);
		System.out.println(u2);
		ElementoCatalogo l2 = getByISBN(700);
		Prestito prestito = savePrestito(u2, l2);*/
	
	}
	
	private static Libro saveLibro() {
		Libro l = new Libro();
		l.setTitolo("Il Libro della Giungla");
		l.setAutore("Rudyard Kipling");
		l.setnPagine(200);
		l.setGenere("Narrativa");
		l.setAnnoPubblicazione(1894);
	
	        elementoDAO.save(l);
		
	        return l;
	}
	private static Rivista saveRivista() {
		Rivista r = new Rivista();
		r.setTitolo("Rivista cane");
		r.setPeriodicita(Periodicita.SETTIMANALE);
		r.setnPagine(29);
		r.setAnnoPubblicazione(2020);
		                 
		elementoDAO.save(r);
		
		return r;
		
	}
	private static Utente saveUtente() {
		Utente u = new Utente();
		u.setNome("Pippo");
		u.setCognome("Baudo");
		u.setDataDiNascita(LocalDate.parse("1936-06-07"));
		
		utDAO.save(u);
		
		return u;
	}

	public static Prestito savePrestito(Utente utente, ElementoCatalogo e) {
		Prestito p = new Prestito();
		p.setElementoPrestato(e);
		p.setDataInizioPrestito(LocalDate.parse("2023-02-05"));
		p.setRestituzionePrevista(p.getDataInizioPrestito().plusDays(30));
		p.setRestituzioneEffettiva(null);
		
		
		PrestitoDAO prestitoDAO = new PrestitoDAO();
		prestitoDAO.save(p);
		
		return p;
		
	}
	private static void delete(ElementoCatalogo el) {
		
		elementoDAO.delete(el);;
		
	}
	private static void deleteViaISBN(long isbn) {
		
		elementoDAO.deleteByISBN(isbn);
		
	}
	private static ElementoCatalogo getByISBN(long isbn) {
	
		return elementoDAO.ricercaByISBN(isbn);
		
	}
	public static void searchAnno(int anno) {
	
		elementoDAO.ricercaByAnno(anno);
		
	}
	private static void GetByAutore(String autore) {

		elementoDAO.ricercaByAutore(autore);
		
	}
	public static void searchTitoloParziale(String titoloParziale) {
		
		elementoDAO.ricercaParzialeTitolo(titoloParziale);
		
	}
	private static Utente UtenteByNumTess(int numeroTessera) {
		
		return utDAO.getByNumTessera(numeroTessera);
		
	}
   private static Prestito PrestitoById(long id) {
		
		return prDAO.getById(id);
		
	}
  //////////
   public static void searchTessera() {
		PrestitoDAO prestitoDAO = new PrestitoDAO();
		prestitoDAO.ricercaByTessera(1);
		
	}
	public static void searchScaduti() {
		PrestitoDAO prestitoDAO = new PrestitoDAO();
		prestitoDAO.ricercaScaduti();
		
	}

}
