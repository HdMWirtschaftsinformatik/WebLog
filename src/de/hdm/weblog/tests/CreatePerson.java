package de.hdm.weblog.tests;
import de.hdm.weblog.BlogAdministration;
import de.hdm.weblog.BlogAdministrationImpl;



public class CreatePerson {

	public static void main(String[] args) {
		
		BlogAdministration adm = new BlogAdministrationImpl();		
		adm.createPerson("Rathke", "Christian", "rathke@hdm-stuttgart.de");		

	}

}
