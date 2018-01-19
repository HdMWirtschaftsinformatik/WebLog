package de.hdm.weblog.tests;

import de.hdm.weblog.server.BlogAdministrationImpl;
import de.hdm.weblog.shared.BlogAdministration;
import de.hdm.weblog.shared.Person;

public class ShowAllPersons {

	public static void main(String[] args) {

		// Test Blogeinträge auslesen

		BlogAdministration adm = new BlogAdministrationImpl();
		// findAll liefert einen Vector mit allen Blogeintrag-Objekten zurück
		// Diese werden ausgegeben
		for (Person person : adm.findAllPersons()) {
			System.out.println(person + " (" +  person.getEmail() + ")");
		}

	}

}
