package client;



public class Kommentar {
	
	public Person person;
	Textbeitrag textbeitrag;
	
	int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Kommentar (Textbeitrag textbeitrag, Person person){
		
		this.textbeitrag = textbeitrag;
	
		this.person = person;
		
		
		
	}

}
