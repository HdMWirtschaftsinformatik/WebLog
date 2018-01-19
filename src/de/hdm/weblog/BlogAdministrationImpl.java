package de.hdm.weblog;

import java.util.Date;
import java.util.Vector;

import de.hdm.weblog.db.BlogeintragMapper;
import de.hdm.weblog.db.KommentarMapper;
import de.hdm.weblog.db.PersonMapper;

public class BlogAdministrationImpl implements BlogAdministration {

	public BlogAdministrationImpl() {

		// the one an only...
		createPerson("Blogger", "Jonny", "blogger@blogspot.com");

	}

	@Override
	public Vector<Blogeintrag> findAll() {
		return BlogeintragMapper.findAll();
	}

	@Override
	public Vector<Blogeintrag> findAllLatestFirst() {
		Vector<Blogeintrag> blogs = findAll();
		blogs.sort(null);
		return blogs;
	}

	@Override
	public Blogeintrag findBlogeintragById(int id) {
		return BlogeintragMapper.findById(id);
	}

	/**
	 * 
	 * @param name
	 * @param vorname
	 * @param email
	 * @return
	 */
	@Override
	public Person createPerson(String name, String vorname, String email) {
		// Personen werden nur neu angelegt, wenn ihre email noch nicht existiert.
		Person pers = PersonMapper.findByEmail(email);
		if (pers != null) {
			return pers;
		}

		pers = new Person(name, vorname, email);
		PersonMapper.insert(pers);

		return pers;

	}

	@Override
	public Person findPersonByEmail(String email) {
		return PersonMapper.findByEmail(email);
	}

	@Override
	public Person findPersonById(int id) {
		return PersonMapper.findById(id);
	}

	@Override
	public Vector<Person> findAllPersons() {
		return PersonMapper.findAll();
	}

	@Override
	public void deletePerson(Person p) {
		PersonMapper.delete(p);
	}

	@Override
	public Kommentar createKommentar(String inhalt, Person autor, Blogeintrag be) {
		Kommentar kom = null;
		if (autor != null && be != null) {
			kom = be.createKommentar(inhalt, autor, new Date());
			KommentarMapper.insert(kom);
		}

		return kom;
	}

	/**
	 * Das Erzeugen von Kommentaren ohne Autor wird "Jonny Blogger" gutgeschrieben.
	 * 
	 * @param inhalt
	 * @param be
	 * @return
	 */
	@Override
	public Kommentar createKommentar(String inhalt, Blogeintrag be) {
		return createKommentar(inhalt, findPersonByEmail("blogger@blogspot.com"), be);
	}

	@Override
	public Blogeintrag createBlogeintrag(String inhalt, Person autor, String titel, String utitel) {

		Blogeintrag blogeintr = null;
		if (autor != null) {
			blogeintr = new Blogeintrag(inhalt, autor, new Date(), titel, utitel);
			BlogeintragMapper.insert(blogeintr);
		}

		return blogeintr;

	}

	@Override
	public Blogeintrag createBlogeintrag(String inhalt, String titel, String utitel) {
		return createBlogeintrag(inhalt, findPersonByEmail("blogger@blogspot.com"), titel, utitel);
	}

	@Override
	public void deleteBlogeintrag(Blogeintrag be) {
		// Kommentarvektor muss ge-clone-t werden, da während der Itaration über
		// den Vector dieser verändert wird.
		for (Object kom : (Vector<?>) be.getKommentare().clone()) {
			deleteKommentar((Kommentar) kom);
		}
		BlogeintragMapper.delete(be);
	}

	@Override
	public void deleteKommentar(Kommentar kom) {
		kom.getBlockeintrag().removeKommentar(kom);
		KommentarMapper.delete(kom);
	}

}
