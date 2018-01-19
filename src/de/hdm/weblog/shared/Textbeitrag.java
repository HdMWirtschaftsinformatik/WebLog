package de.hdm.weblog.shared;


import java.io.Serializable;
import java.util.Date;

public class Textbeitrag implements Comparable<Textbeitrag>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Date datum;
	private String inhalt;
	private Person autor;
	
	public Textbeitrag( String inhalt){
		
		//this.datum = date;
		this.inhalt = inhalt;
	}
	
	public Textbeitrag() {}

	public boolean equals(Object o) {
		if (o instanceof Textbeitrag) {
			return id == ((Textbeitrag) o).getId();
		} else {
			return false;
		}
	}
	
	/**
	 * for sorting oldest 
	 */
	public int compareTo(Textbeitrag tb){
		return tb.getDatum().compareTo(getDatum());
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
