package de.hdm.weblog;


import java.sql.SQLException;
import java.util.Vector;

import de.hdm.weblog.db.BlogMapper;
import de.hdm.weblog.db.KommentarMapper;
import de.hdm.weblog.db.PersonMapper;
import de.hdm.weblog.db.TextbeitragMapper;




public class BlogAdministration {
	
	private Blogeintrag blogeintrag = null;
	private BlogMapper bMapper = null;
	private PersonMapper pMapper = null;
	private TextbeitragMapper tMapper = null;
	private KommentarMapper kMapper = null;
	
	public BlogAdministration(){
		this.bMapper = BlogMapper.blogMapper();
		this.pMapper = PersonMapper.personMapper();
		this.tMapper = TextbeitragMapper.textbeitragMapper();
		this. kMapper = KommentarMapper.kommentarMapper();
		

	}
	
	
	
	public Vector<Blogeintrag> findAll() throws SQLException{
		
		return bMapper.findAll();
		
		
		
	}
	
	
	
	
	
	
	public Person createPerson() throws SQLException {
		
		
		//Daten kommen später über servlet
		String name = "Fabian";
		String vorname = "Tschullik";
		String email = "ft027@hdm-stuttgart.de";
		
		Person pers = new Person(name, vorname, email);
		
		if (pMapper.checkIfExists(pers) == null)
			
		{
			return pMapper.add(pers);
		
		}
		
		return pMapper.checkIfExists(pers);
				
	}
	
	
	
	
	public Integer createKommentar(Integer id ) throws SQLException   {
		
		
		Person pers;
	    pers = createPerson();
		
		String inhaltKommentar = "Das ist ein Kommentar";
		Textbeitrag txtb = new Textbeitrag(inhaltKommentar);
		
		txtb = tMapper.add(txtb, pers);
		
		Kommentar kommentar = new Kommentar(txtb, pers);
		kommentar.setId(txtb.getId());
		
		
		kMapper.add(kommentar, id);
		
		
		
		
		
		return kommentar.getId();
	}
	
	
	public Vector<Kommentar> geKommentarByID(Integer id) throws SQLException{
		
		return kMapper.findAllforID(55);
		
	}
	
	
	
	public void createBlogeintrag() throws SQLException{
		
		
			
		//Textbeitrag anlegen
		//Daten aus Formular lesen

		String inhalt = "Dies ist der Inhalt aus dem Formular";
		Textbeitrag txtb = new Textbeitrag(inhalt);
		
		//Blogeintrag anlegen
		//Daten aus Formular lesen
		String titel = "Titel";
		String untertitel = "Untertitel";
		
		Blogeintrag blogeintr = new Blogeintrag(txtb, createPerson(), inhalt, untertitel);
		
		
	bMapper.add(blogeintr);
		
			
	}
	
	public void deleteBlogeintrag(int id) throws SQLException{
		
		bMapper.delete(id);
			
	}
	
	
	

}
