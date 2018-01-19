package de.hdm.weblog.tests;
import java.util.Vector;

import de.hdm.weblog.server.BlogAdministrationImpl;
import de.hdm.weblog.shared.BlogAdministration;
import de.hdm.weblog.shared.Blogeintrag;



public class CreateKommentar {

	public static void main(String[] args) {
		
		BlogAdministration adm = new BlogAdministrationImpl();	
		Vector<Blogeintrag> blogs = adm.findAll();
		if (blogs.size()>0) {
			adm.createKommentar("Das ist der Inhalt des Kommentars", blogs.get(blogs.size()-1));		
		}
		ShowAll.main(new String[0]);
	}

}
