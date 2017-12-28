package de.hdm.weblog.tests;
import java.util.Vector;

import de.hdm.weblog.BlogAdministration;
import de.hdm.weblog.Person;



public class deletePerson {

	public static void main(String[] args) {
		
		BlogAdministration adm = new BlogAdministration();
		Vector<Person> persons = adm.findAllPersons();
		if (persons.size()>0) {
			adm.deletePerson(persons.get(persons.size()-1));
		}
		
		ShowAllPersons.main(new String[0]);
	}

}
