package de.hdm.weblog;

import java.util.Vector;

public interface BlogAdministration {

	Vector<Blogeintrag> findAll();

	Vector<Blogeintrag> findAllLatestFirst();

	Blogeintrag findBlogeintragById(int id);

	/**
	 * 
	 * @param name
	 * @param vorname
	 * @param email
	 * @return
	 */
	Person createPerson(String name, String vorname, String email);

	Person findPersonByEmail(String email);

	Person findPersonById(int id);

	Vector<Person> findAllPersons();

	void deletePerson(Person p);

	Blogeintrag.Kommentar createKommentar(String inhalt, Person autor, Blogeintrag be);

	/**
	 * Das Erzeugen von Blogeinträgen ohne Autor wird "Jonny Blogger" guteschrieben.
	 * @param inhalt
	 * @param be
	 * @return
	 */
	Blogeintrag.Kommentar createKommentar(String inhalt, Blogeintrag be);

	Blogeintrag createBlogeintrag(String inhalt, Person autor, String titel, String utitel);

	Blogeintrag createBlogeintrag(String inhalt, String titel, String utitel);

	void deleteBlogeintrag(Blogeintrag be);

	void deleteKommentar(Blogeintrag.Kommentar kom);

}