package de.hdm.weblog;


import java.util.Date;

public class Textbeitrag {
	
	int id;
	Date datum;
	String inhalt;
	Person autor;
	
	public Textbeitrag( String inhalt){
		
		//this.datum = date;
		this.inhalt = inhalt;
	};

	public boolean equals(Object o) {
		if (o instanceof Textbeitrag) {
			return id == ((Textbeitrag) o).getId();
		}
		return super.equals(o);
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public String getInhalt() {
		return inhalt;
	}
	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}
	public Person getAutor() {
		return autor;
	}
	public void setAutor(Person p){
		autor = p;
	}
}
