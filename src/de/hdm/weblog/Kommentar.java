package de.hdm.weblog;



public class Kommentar extends Textbeitrag {
	
	Blogeintrag be;
	
	public Kommentar(String inhalt) {
		super(inhalt);
	}
	
	public Blogeintrag getBlockeintrag() {
		return be;
	}
	
	public void setBlogeintrag(Blogeintrag b) {
		be = b;
	}

	public String toString() {
		return getAutor().toString() + ": "  + getInhalt(); 
	}

}
