import java.time.LocalDateTime;


public class BlogEintrag {
	
	public LocalDateTime getDatum() {
		return datum;
	}

	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}

	LocalDateTime datum;
	
	//Constructor
	public BlogEintrag(LocalDateTime neuesDatum){
		datum = neuesDatum;
		
	}
	
	
	class Inhalt {
	
	String titel;
	String untertitel;
	String text;
	
	//Constructor
	public Inhalt(String neuerTitel, String neuerUntertitel, String neuerText){
		
		titel = neuerTitel;
		untertitel = neuerUntertitel;
		text = neuerText;
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
		
	}
	
	class Autor {
		
		String name;
		String vorname;
		String email;
		
		//Constructor
		public Autor(String neuerName, String neuerVorname, String neueEmail){
			name = neuerName;
			vorname = neuerVorname;
			email = neueEmail;
			
			
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getVorname() {
			return vorname;
		}
		public void setVorname(String vorname) {
			this.vorname = vorname;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
	}



}
