package de.hdm.weblog.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.weblog.Kommentar;
import de.hdm.weblog.Person;
import de.hdm.weblog.Textbeitrag;

public class KommentarMapper {
	

	
	private static KommentarMapper kommentarMapper = null;
	private Kommentar kommentar = null;
	private int id;

	
	 public KommentarMapper() {
	    }

	 
	  public static KommentarMapper kommentarMapper() {
	        if (kommentarMapper == null) {
	            kommentarMapper = new KommentarMapper();
	        }
	        return kommentarMapper;
	    }
	
	
	
	public void add(Kommentar kommentar, Integer id) throws SQLException {
		
		
		
		this.kommentar = kommentar;
		this.id = id;
		

		
		
		
		Connection con = DBConnection.connection();
		
		String insertTableSQL = "INSERT INTO kommentar "
				+ "(id, fk_blogeintrag) VALUES "
				+ "(?,?)";
		PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
		preparedStatement.setInt(1, kommentar.getId());
		preparedStatement.setInt(2, id);
		
	
		// execute insert SQL stetement
		preparedStatement.executeUpdate();
		

		
		
		
	}
	
	public Vector<Kommentar> findAllforID(Integer id) throws SQLException{
		
		Connection con = DBConnection.connection();

		 Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("select id from kommentar where fk_blogeintrag = "+id);
         
    	 Vector<Kommentar> result = new Vector<Kommentar>();

         
         while (rs.next()) {
        	 
        	 int kommentarID = rs.getInt("id");
        	 System.out.println(kommentarID);
        	 
        	 
        	 Connection con2 = DBConnection.connection();
    		 Statement stmt2 = con2.createStatement();
             ResultSet rs2 = stmt2.executeQuery("SELECT * from textbeitrag join person on person.id = textbeitrag.fk_person where textbeitrag.id = "+kommentarID);
             
             
             while (rs2.next()) {
            	 
            String name = rs2.getString("name");
            String vorname = rs2.getString("vorname");
            String email = rs2.getString("email");
            
            	 
            Person pers = new Person(name, vorname, email);	
            
            String inhalt = rs2.getString("inhalt");
            Date datum = rs2.getDate("datum");
            
            Textbeitrag txtbt = new Textbeitrag(inhalt);
            txtbt.setDatum(datum);
            	 
            	 
            Kommentar kommentar = new Kommentar(txtbt, pers);
            result.addElement(kommentar);
             System.out.println(rs2.getString("inhalt"));
        	 
        	 
             }	
		
		
	}
		return result;
	}
	

}
