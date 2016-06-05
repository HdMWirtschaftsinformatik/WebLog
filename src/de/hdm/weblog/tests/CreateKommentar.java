package de.hdm.weblog.tests;
import java.util.Vector;

import de.hdm.weblog.BlogAdministration;
import de.hdm.weblog.Blogeintrag;
import de.hdm.weblog.Person;



public class CreateKommentar {

	public static void main(String[] args) {
		
		BlogAdministration adm = new BlogAdministration();		
		Person p = adm.findPersonByEmail("rathke@hdm-stuttgart.de");
		Vector<Blogeintrag> blogs = adm.findAll();
		if (blogs.size()>0) {
			adm.createKommentar("Das ist der Inhalt des Kommentars", p, blogs.get(0));		
		}
	}

}
