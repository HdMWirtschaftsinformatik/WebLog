package de.hdm.weblog.tests;
import java.util.Vector;

import de.hdm.weblog.server.BlogAdministrationImpl;
import de.hdm.weblog.shared.BlogAdministration;
import de.hdm.weblog.shared.Person;



public class deletePerson {

	public static void main(String[] args) {
		
		BlogAdministration adm = new BlogAdministrationImpl();
		Vector<Person> persons = adm.findAllPersons();
		if (persons.size()>0) {
			adm.deletePerson(persons.get(persons.size()-1));
		}
		
		ShowAllPersons.main(new String[0]);
	}

}
