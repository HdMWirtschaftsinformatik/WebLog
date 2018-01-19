package de.hdm.weblog.tests;

import de.hdm.weblog.server.BlogAdministrationImpl;
import de.hdm.weblog.shared.BlogAdministration;
import de.hdm.weblog.shared.Blogeintrag;
import de.hdm.weblog.shared.Kommentar;

public class ShowAll {

	public static void main(String[] args) {

		// Test Blogeinträge auslesen

		BlogAdministration adm = new BlogAdministrationImpl();
		// findAll liefert einen Vector mit allen Blogeintrag-Objekten zurück
		// Diese werden ausgegeben
		for (Blogeintrag be : adm.findAll()) {
			System.out.println(be);
			for (Kommentar kom : be.getKommentare()) {
				System.out.println("     " + kom);
			}
		}

	}

}
