package de.hdm.weblog;

import java.util.Date;
import java.util.Vector;

public class Blogeintrag extends Textbeitrag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Vector<Kommentar> kommentare = new Vector<Kommentar>();

	private String titel;
	private String untertitel;
	
	
	public class Kommentar extends Textbeitrag {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Kommentar(String inhalt) {
			super(inhalt);
		}
		
		public Kommentar() {
		}
		
		public Blogeintrag getBlockeintrag() {
			return getThisBlogeintrag();
		}

		public String toString() {
			return getAutor().toString() + ": "  + getInhalt(); 
		}

	}
	
	

	public Blogeintrag(String inhalt) {
		super(inhalt);
	}
	
	public Blogeintrag() {}

	public Blogeintrag(String inhalt, Person autor, Date datum, String titel, String utitel) {
		this(inhalt);
		this.titel = titel;
		this.untertitel = utitel;
		setAutor(autor);
		setDatum(datum);
	}
	
	public Kommentar createKommentar(String inhalt, Person autor, Date datum) {
		Kommentar kom = new Kommentar(inhalt);
		kom.setAutor(autor);
		kom.setDatum(datum);
		kommentare.add(kom);
		return kom;
	}
	
	private Blogeintrag getThisBlogeintrag() {
		return this;
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

	public Vector<Kommentar> getKommentare() {
		return kommentare;
	}

	public void setKommentare(Vector<Kommentar> koms) {
		kommentare = koms;
	}

	public void addKommentar(Kommentar k) {
		kommentare.add(k);
		
	}

	public void removeKommentar(Kommentar k) {
		kommentare.remove(k);
	}

	public String toString() {
		return getAutor().toString() + ": " + titel + " (" + untertitel + ") " + ": " + getInhalt();
	}
	

}
