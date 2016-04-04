import java.time.LocalDateTime;


public class start {

	public static void main(String[] args) {
		
		
		BlogEintrag blogEintrag = new BlogEintrag(LocalDateTime.now());
		BlogEintrag.Inhalt inhalt = blogEintrag.new Inhalt("Mein Urlaub", "in Griecheland", "blablabla");
		BlogEintrag.Autor autor = blogEintrag.new Autor("Tschullik", "Fabian","ft027æhdm-stuttgart.de");
		
		System.out.println("Datum: "+ blogEintrag.getDatum());
		System.out.println("Titel: "+ inhalt.getTitel());
		System.out.println("Untertitel: "+ inhalt.getUntertitel());
		System.out.println("Text: "+ inhalt.getText());
		System.out.println("Autor: "+ autor.getVorname()+ " " + autor.getName());
		

		

	}

}
