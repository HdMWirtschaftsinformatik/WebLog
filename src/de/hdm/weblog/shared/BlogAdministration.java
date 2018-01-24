package de.hdm.weblog.shared;

import java.util.Vector;



import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
@RemoteServiceRelativePath("weblog")

public interface BlogAdministration extends RemoteService {

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

	Kommentar createKommentar(String inhalt, Person autor, Blogeintrag be);

	/**
	 * Das Erzeugen von Blogeinträgen ohne Autor wird "Jonny Blogger" guteschrieben.
	 * @param inhalt
	 * @param be
	 * @return
	 */
	Kommentar createKommentar(String inhalt, Blogeintrag be);

	Blogeintrag createBlogeintrag(String inhalt, Person autor, String titel, String utitel);

	Blogeintrag createBlogeintrag(String inhalt, String titel, String utitel);

	void deleteBlogeintrag(Blogeintrag be);

	void deleteKommentar(Kommentar kom);

}