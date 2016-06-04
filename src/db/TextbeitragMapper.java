package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import client.Person;
import client.Textbeitrag;

public class TextbeitragMapper {
	

	private static TextbeitragMapper textbeitragMapper = null;
	private Textbeitrag textbeitrag;

	
	 public TextbeitragMapper() {
	    }

	 
	  public static TextbeitragMapper textbeitragMapper() {
	        if (textbeitragMapper == null) {
	            textbeitragMapper = new TextbeitragMapper();
	        }
	        return textbeitragMapper;
	    }
	 

	  
	  
	  public Textbeitrag add(Textbeitrag textbeitrag, Person person) throws SQLException{
		
		  this.textbeitrag = textbeitrag;
			
			Connection con = DBConnection.connection();
			
			//Person erstellen
			
			String insertTableSQL = "INSERT INTO textbeitrag "
					+ "(inhalt, fk_person) VALUES "
					+ "(?,?)";
			PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, textbeitrag.getInhalt());
			preparedStatement.setInt(2, person.getId());
	
			
		
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
			
			ResultSet key = preparedStatement.getGeneratedKeys();
			key.next();
			int id = key.getInt(1);
			textbeitrag.setId(id);
		  
		  
		  
		  
		  return textbeitrag;
		  
		  
	  }
	  
	  
	  
	  
	  
	  
	  
}
