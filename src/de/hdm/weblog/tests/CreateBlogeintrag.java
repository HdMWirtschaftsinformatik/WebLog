package de.hdm.weblog.tests;
import de.hdm.weblog.BlogAdministration;
import de.hdm.weblog.BlogAdministrationImpl;



public class CreateBlogeintrag {

	public static void main(String[] args) {
		
		BlogAdministration adm = new BlogAdministrationImpl();	
		adm.createBlogeintrag("Das ist der Inhalt des Blogeintrags", "Titel", "Untertitel");		
		
		ShowAll.main(new String[0]);

	}

}
