package de.hdm.weblog;

import java.util.Date;
import java.util.Vector;

import de.hdm.weblog.db.BlogeintragMapper;
import de.hdm.weblog.db.KommentarMapper;
import de.hdm.weblog.db.PersonMapper;

public class BlogAdministration {

	private BlogeintragMapper bMapper = null;
	private PersonMapper pMapper = null;
	private KommentarMapper kMapper = null;

	public BlogAdministration() {
		this.bMapper = BlogeintragMapper.blogeintragMapper();
		this.pMapper = PersonMapper.personMapper();
		this.kMapper = KommentarMapper.kommentarMapper();
		
		createPerson("Blogger", "Jonny", "blogger");

	}

	public Vector<Blogeintrag> findAll() {
		Vector<Blogeintrag> blogs = bMapper.findAll();
		for (Blogeintrag be : blogs) {
			be.setKommentare(kMapper.findAllForBlogeintrag(be));
		}
		return blogs;
	}
	
	public Vector<Blogeintrag> findAllLatestFirst() {
		Vector<Blogeintrag> blogs = findAll();
		blogs.sort(null);
		return blogs;
	}
	
	public Blogeintrag findBlogeintragById(int id) {
		return bMapper.findById(id);
	}

	public Person createPerson(String name, String vorname, String email) {

		Person pers = pMapper.findByEmail(email);
		if (pers != null) {
			return pers; 
		}
		
		pers = new Person(name, vorname, email);		
		pMapper.insert(pers);
		
		return pers;

	}
	
	public Person findPersonByEmail(String email) {
		return pMapper.findByEmail(email);
	}
	
	public Person findPersonById(int id) {
		return pMapper.findById(id);
	}

	public Kommentar createKommentar(String inhalt, Person autor, Blogeintrag be) {

		Kommentar kommentar = new Kommentar(inhalt);
		kommentar.setAutor(autor);
		kommentar.setDatum(new Date());
		
		kommentar.setBlogeintrag(be);
		be.addKommentar(kommentar);
		
		kMapper.insert(kommentar);

		return kommentar;
	}
	
	
	public Kommentar createKommentar(String inhalt, Blogeintrag be) {
		return createKommentar(inhalt, findPersonByEmail("blogger"), be);
	}
	
	public Kommentar findKommentarById(int id) {
		return kMapper.findById(id);
	}

	public Blogeintrag createBlogeintrag(String inhalt, Person autor, String titel, String utitel) {

		// Textbeitrag anlegen
		// Daten aus Formular lesen

		Blogeintrag blogeintr = new Blogeintrag(inhalt, autor, new Date(), titel, utitel);

		bMapper.insert(blogeintr);
		
		return blogeintr;

	}
	
	public Blogeintrag createBlogeintrag(String inhalt, String titel, String utitel) {
		return createBlogeintrag(inhalt, findPersonByEmail("blogger"), titel, utitel);
	}

	public void deleteBlogeintrag(Blogeintrag be) {
		for (Kommentar kom : be.getKommentare()) {
			kMapper.delete(kom);
		}
		bMapper.delete(be);
	}
	
	public void deleteKommentar(Kommentar kom) {
		kom.getBlockeintrag().removeKommentar(kom);
		kMapper.delete(kom);
	}

}
