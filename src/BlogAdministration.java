

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import client.Blogeintrag;
import client.Person;
import client.Textbeitrag;
import db.BlogMapper;




public class BlogAdministration {
	
	private Blogeintrag blogeintrag = null;
	private BlogMapper bMapper = null;
	
	public BlogAdministration(){
		this.bMapper = BlogMapper.blogMapper();

	}
	
	public void createBlogeintrag() throws SQLException{
		
		//Person Anlegen
		//Daten aus Formular lesen
		String value1 = "1";
		String value2 = "2";
		String value3 = "3";
		
		Person pers = new Person(value1, value2, value3);
			
		//Textbeitrag anlegen
		//Daten aus Formular lesen

		String inhalt = "Dies ist der Inhalt aus dem Formular";
		Textbeitrag txtb = new Textbeitrag(inhalt);
		
		//Blogeintrag anlegen
		//Daten aus Formular lesen
		String titel = "Titel";
		String untertitel = "Untertitel";
		
		Blogeintrag blogeintr = new Blogeintrag(txtb, pers, inhalt, untertitel);
		
		
	bMapper.add(blogeintr);
		
			
	}
	
	public void deleteBlogeintrag(int id) throws SQLException{
		
		bMapper.delete(id);
			
	}
	
	
	

}
