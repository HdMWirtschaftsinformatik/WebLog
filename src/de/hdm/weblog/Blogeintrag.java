package de.hdm.weblog;

import java.util.Date;
import java.util.Vector;

public class Blogeintrag extends Textbeitrag {

	Vector<Kommentar> kommentare = new Vector<Kommentar>();

	public String titel;
	public String untertitel;

	public Blogeintrag(String inhalt) {
		super(inhalt);
	}

	public Blogeintrag(String inhalt, Person autor, Date datum, String titel, String utitel) {
		this(inhalt);
		this.titel = titel;
		this.untertitel = utitel;
		setAutor(autor);
		setDatum(datum);
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
		return autor.toString() + ": " + titel + " (" + untertitel + ") " + ": " + inhalt;
	}

}
