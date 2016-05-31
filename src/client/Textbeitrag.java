package client;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Textbeitrag {
	
	int id;
	Date datum;
	String inhalt;
	
	public Textbeitrag( String inhalt){
		
		//this.datum = date;
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
