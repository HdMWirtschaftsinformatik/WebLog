package de.hdm.weblog.tests;
import de.hdm.weblog.BlogAdministration;
import de.hdm.weblog.Person;



public class CreateBlogeintrag {

	public static void main(String[] args) {
		
		BlogAdministration adm = new BlogAdministration();		
		Person p = adm.findPersonByEmail("rathke@hdm-stuttgart.de");
		adm.createBlogeintrag("Das ist der Inhalt des Blogeintrags", p, "Titel", "Untertitel");		
		
		ShowAll.main(new String[0]);

	}

}
