package client;

import java.sql.Date;

public class Blogeintrag {
	public int id;
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}
	public String titel;
	public String untertitel;
	
	//Ein Blogeintrag besteht aus einem Textbeitrag und einer Person
	public Textbeitrag textbeitrag;
	public Person person;
	
	
	public Blogeintrag (Textbeitrag textbeitrag, Person person, String titel, String untertitel){
		
		
		
		this.textbeitrag = textbeitrag;
		this.person = person;
		this.titel = titel;
		this.untertitel = untertitel;
		
		
	}
	
		
	
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getUntertitel() {
		return untertitel;
	}
	public void setUntertitel(String untertitel) {
		this.untertitel = untertitel;
	}
	
	
	

}
