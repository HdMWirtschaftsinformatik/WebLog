package de.hdm.weblog.tests;

import de.hdm.weblog.BlogAdministration;
import de.hdm.weblog.BlogAdministrationImpl;
import de.hdm.weblog.Blogeintrag;
import de.hdm.weblog.Kommentar;

public class ShowAll {

	public static void main(String[] args) {

		// Test Blogeintr�ge auslesen

		BlogAdministration adm = new BlogAdministrationImpl();
		// findAll liefert einen Vector mit allen Blogeintrag-Objekten zur�ck
		// Diese werden ausgegeben
		for (Blogeintrag be : adm.findAll()) {
			System.out.println(be);
			for (Kommentar kom : be.getKommentare()) {
				System.out.println("     " + kom);
			}
		}

	}

}
