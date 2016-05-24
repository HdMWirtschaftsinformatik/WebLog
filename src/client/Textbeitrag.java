package client;

import java.sql.Date;

public class Textbeitrag {
	
	int id;
	Date datum;
	String inhalt;
	
	public Textbeitrag(Date datum, String inhalt){
		
		this.datum = datum;
		this.inhalt = inhalt;
	};
	
	
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
}
