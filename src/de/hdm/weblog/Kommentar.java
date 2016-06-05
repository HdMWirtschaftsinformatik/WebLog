package de.hdm.weblog;




public class Kommentar extends Textbeitrag {
	
	Blogeintrag beitrag;
	
	public Kommentar(String inhalt) {
		super(inhalt);
	}
	
	public Blogeintrag getBeitrag() {
		return beitrag;		
	}
	public void setBeitrag(Blogeintrag b){
		beitrag = b;
	}

	public String toString() {
		return autor.toString() + ": "  + inhalt; 
	}

}
