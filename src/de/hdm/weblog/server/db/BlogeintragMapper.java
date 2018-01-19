package de.hdm.weblog.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.weblog.shared.Blogeintrag;

public class BlogeintragMapper {

	public static Vector<Blogeintrag> findAll() {
		Connection con = DBConnection.connection();
		Vector<Blogeintrag> result = new Vector<Blogeintrag>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from textbeitrag, blogeintrag "
					+ "where textbeitrag.id = blogeintrag.id " + "ORDER BY textbeitrag.datum");

			while (rs.next()) {
				Blogeintrag be = new Blogeintrag(rs.getString("inhalt"));

				// Blogeintrag erstellen
				be.setId(rs.getInt("id"));
				be.setDatum(rs.getDate("datum"));
				be.setTitel(rs.getString("titel"));
				be.setUntertitel(rs.getString("untertitel"));
				be.setAutor(PersonMapper.findById(rs.getInt("autor")));
				KommentarMapper.findAllForBlogeintrag(be);

				result.addElement(be);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zurueckgeben
		return result;
	}

	public static Blogeintrag findById(int id) {
		Connection con = DBConnection.connection();
		Blogeintrag be = null;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from textbeitrag, blogeintrag "
					+ "where textbeitrag.id = blogeintrag.id and blogeintrag.id = " + id);

			if (rs.next()) {
				be = new Blogeintrag(rs.getString("inhalt"));

				// Blogeintrag erstellen
				be.setId(rs.getInt("id"));
				be.setDatum(rs.getDate("datum"));
				be.setTitel(rs.getString("titel"));
				be.setUntertitel(rs.getString("untertitel"));
				be.setAutor(PersonMapper.findById(rs.getInt("autor")));
				KommentarMapper.findAllForBlogeintrag(be);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return be;
	}

	public static void insert(Blogeintrag blogeintrag) {

		TextbeitragMapper.insert(blogeintrag);

		Connection con = DBConnection.connection();
		try {
			PreparedStatement statement = con
					.prepareStatement("INSERT INTO blogeintrag (id, titel, untertitel) VALUES (?, ?, ?)");
			statement.setInt(1, blogeintrag.getId());
			statement.setString(2, blogeintrag.getTitel());
			statement.setString(3, blogeintrag.getUntertitel());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void delete(Blogeintrag blogeintrag) {
		delete(blogeintrag.getId());
	}

	public static void delete(int id) {
		TextbeitragMapper.delete(id);
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM blogeintrag " + "WHERE id = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	public static void removeTable() {
		KommentarMapper.removeTable();
		Connection con = DBConnection.connection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DROP TABLE blogeintrag");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void createTable() {
		String sqlString = "CREATE TABLE blogeintrag (\n" + 
				"  id int NOT NULL,\n" + 
				"  titel varchar(45) DEFAULT NULL,\n" + 
				"  untertitel varchar(45) DEFAULT NULL,\n" + 
				"  PRIMARY KEY (id)\n" + 
				" )";
		Connection con = DBConnection.connection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sqlString);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

}
