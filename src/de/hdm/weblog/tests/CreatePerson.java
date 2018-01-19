package de.hdm.weblog.tests;
import de.hdm.weblog.server.BlogAdministrationImpl;
import de.hdm.weblog.shared.BlogAdministration;



public class CreatePerson {

	public static void main(String[] args) {
		
		BlogAdministration adm = new BlogAdministrationImpl();		
		adm.createPerson("Rathke", "Christian", "rathke@hdm-stuttgart.de");		

	}

}
