import java.sql.SQLException;
import java.util.Vector;

import client.Blogeintrag;
import db.BlogMapper;



public class Test {

	public static void main(String[] args) throws SQLException {
		
		//Test Blogeinträge auslesen
		
		BlogMapper bm = new BlogMapper();
		
		Vector<Blogeintrag> b = new Vector<Blogeintrag>();
		b = bm.findAll();
		
		for (int i = 0; i<b.size(); i++){
		System.out.println(b.get(i).getTitel());
		System.out.println(b.get(i).getUntertitel());
		System.out.println(b.get(i).person.getName());
		System.out.println(b.get(i).textbeitrag.getInhalt());
		}
		
		
		//Blogeintrag anlegen
		
		bm.add(b.get(0));
		
		
		
		

	}

}
