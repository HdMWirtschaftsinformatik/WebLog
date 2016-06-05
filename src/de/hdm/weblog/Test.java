package de.hdm.weblog;
import java.sql.SQLException;
import java.util.Vector;

import de.hdm.weblog.db.BlogMapper;



public class Test {

	public static void main(String[] args) throws SQLException {
		
		//Test Blogeinträge auslesen
		
		BlogAdministration adm = new BlogAdministration();
		
		
		Vector<Blogeintrag> b = new Vector<Blogeintrag>();
		
		// Ruft die Methode findAll in der Klasse BlogMapper auf
		b = adm.findAll();
		
		// findAll liefert einen Vector mit allen Blogeintrag-Objekten zurück
		// Diese werden ausgegeben
		for (int i = 0; i<b.size(); i++){
		System.out.println(b.get(i).getTitel());
		System.out.println(b.get(i).getUntertitel());
		System.out.println(b.get(i).person.getName());
		System.out.println(b.get(i).textbeitrag.getInhalt());
		}
		
		
		adm.createBlogeintrag();
		
		adm.createKommentar(55);
		
		
		Vector<Kommentar> kommentare = adm.geKommentarByID(55);
		
		
		
		System.out.println(kommentare.get(0).person.getVorname());
		

	}

}
