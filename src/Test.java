import java.sql.SQLException;
import java.util.Vector;

import client.Blogeintrag;
import db.BlogMapper;



public class Test {

	public static void main(String[] args) throws SQLException {
		
		//Test Blogeinträge auslesen
		
		BlogMapper bm = new BlogMapper();
		
		Vector<Blogeintrag> b = new Vector<Blogeintrag>();
		
		// Ruft die Methode findAll in der Klasse BlogMapper auf
		b = bm.findAll();
		
		// findAll liefert einen Vector mit allen Blogeintrag-Objekten zurück
		// Diese werden ausgegeben
		for (int i = 0; i<b.size(); i++){
		System.out.println(b.get(i).getTitel());
		System.out.println(b.get(i).getUntertitel());
		System.out.println(b.get(i).person.getName());
		System.out.println(b.get(i).textbeitrag.getInhalt());
		}
		
		
		//Blogeintrag anlegen
		
		//Ruft die Methode add in der Klasse BlogMapper auf.
		//Dieser Methode wird ein Blogeintrag übergeben
		bm.add(b.get(0));
		
		BlogAdministration adm = new BlogAdministration();
		
		adm.createBlogeintrag();
		adm.deleteBlogeintrag(3);
		
		

	}

}
