package client;

public class Person {
	
	int id;
	String name;
	String vorname;
	String email;
	
	
	public Person(String name, String vorname, String email){
		
		this.name = name;
		this.vorname = vorname;
		this.email = email;
		
		
	};
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
