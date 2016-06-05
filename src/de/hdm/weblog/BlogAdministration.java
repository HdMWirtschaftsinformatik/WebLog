package de.hdm.weblog;

import java.util.Date;
import java.util.Vector;

import de.hdm.weblog.db.BlogMapper;
import de.hdm.weblog.db.KommentarMapper;
import de.hdm.weblog.db.PersonMapper;

public class BlogAdministration {

	private BlogMapper bMapper = null;
	private PersonMapper pMapper = null;
	private KommentarMapper kMapper = null;

	public BlogAdministration() {
		this.bMapper = BlogMapper.blogMapper();
		this.pMapper = PersonMapper.personMapper();
		this.kMapper = KommentarMapper.kommentarMapper();

	}

	public Vector<Blogeintrag> findAll() {
		Vector<Blogeintrag> blogs = bMapper.findAll();
		for (Blogeintrag be : blogs) {
			be.setKommentare(kMapper.findAllForBlogeintrag(be));
		}
		return blogs;
	}

	public Person createPerson(String name, String vorname, String email) {

		Person pers = pMapper.findByEmail(email);
		if (pers != null) {
			return pers; 
		}
		
		pers = new Person(name, vorname, email);
		pMapper.add(pers);
		
		return pers;

	}
	
	public Person findPersonByEmail(String email) {
		return pMapper.findByEmail(email);
	}

	public Kommentar createKommentar(String inhalt, Person autor, Blogeintrag be) {

		Kommentar kommentar = new Kommentar(inhalt);
		kommentar.setBeitrag(be);
		kommentar.setAutor(autor);
		kommentar.setDatum(new Date());
		be.addKommentar(kommentar);

		kMapper.add(kommentar);

		return kommentar;
	}

	public Blogeintrag createBlogeintrag(String inhalt, Person autor, String titel, String utitel) {

		// Textbeitrag anlegen
		// Daten aus Formular lesen

		Blogeintrag blogeintr = new Blogeintrag(inhalt, autor, new Date(), titel, utitel);

		bMapper.add(blogeintr);
		
		return blogeintr;

	}

	public void deleteBlogeintrag(Blogeintrag be) {
		for (Kommentar kom : be.getKommentare()) {
			deleteKommentar(kom);
		}
		bMapper.delete(be);
	}
	
	public void deleteKommentar(Kommentar kom) {
		kom.getBeitrag().removeKommentar(kom);
		kMapper.delete(kom);
	}

}
