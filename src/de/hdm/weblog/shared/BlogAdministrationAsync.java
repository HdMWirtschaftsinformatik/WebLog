package de.hdm.weblog.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BlogAdministrationAsync {

	void createBlogeintrag(String inhalt, Person autor, String titel, String utitel,
			AsyncCallback<Blogeintrag> callback);

	void createBlogeintrag(String inhalt, String titel, String utitel, AsyncCallback<Blogeintrag> callback);

	void createKommentar(String inhalt, Person autor, Blogeintrag be, AsyncCallback<Kommentar> callback);

	void createKommentar(String inhalt, Blogeintrag be, AsyncCallback<Kommentar> callback);

	void createPerson(String name, String vorname, String email, AsyncCallback<Person> callback);

	void deleteBlogeintrag(Blogeintrag be, AsyncCallback<Void> callback);

	void deleteKommentar(Kommentar kom, AsyncCallback<Void> callback);

	void deletePerson(Person p, AsyncCallback<Void> callback);

	void findAll(AsyncCallback<Vector<Blogeintrag>> callback);

	void findAllLatestFirst(AsyncCallback<Vector<Blogeintrag>> callback);

	void findAllPersons(AsyncCallback<Vector<Person>> callback);

	void findBlogeintragById(int id, AsyncCallback<Blogeintrag> callback);

	void findPersonByEmail(String email, AsyncCallback<Person> callback);

	void findPersonById(int id, AsyncCallback<Person> callback);

}
