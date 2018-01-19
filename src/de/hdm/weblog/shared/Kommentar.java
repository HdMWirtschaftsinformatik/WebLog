package de.hdm.weblog.shared;

public class Kommentar extends Textbeitrag {
	private static final long serialVersionUID = 1L;
	
	private Blogeintrag blogeintrag;

	public Kommentar(String inhalt) {
		super(inhalt);
	}
	
	public Kommentar() {
	}
	
	public Blogeintrag getBlockeintrag() {
		return blogeintrag;
	}
	
	public void setBlogeintrag(Blogeintrag be) {
		blogeintrag = be;
	}

	public String toString() {
		return getAutor().toString() + ": "  + getInhalt(); 
	}
}
