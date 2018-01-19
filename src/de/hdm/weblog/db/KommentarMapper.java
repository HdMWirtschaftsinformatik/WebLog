package de.hdm.weblog.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.hdm.weblog.Blogeintrag;
import de.hdm.weblog.Kommentar;

public class KommentarMapper {

	public static void findAllForBlogeintrag(Blogeintrag be) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from textbeitrag, kommentar "
					+ "where textbeitrag.id = kommentar.id and kommentar.blogeintrag = " + be.getId());

			while (rs.next()) {
				Kommentar kom = be.createKommentar(rs.getString("inhalt"),
						PersonMapper.findById(rs.getInt("autor")),
						rs.getDate("datum"));
				kom.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insert(Kommentar kommentar) {

		TextbeitragMapper.insert(kommentar);
		Connection con = DBConnection.connection();
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate("INSERT INTO kommentar (id, blogeintrag) VALUES (" + kommentar.getId() + ", "
					+ kommentar.getBlockeintrag().getId() + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void delete(Kommentar kommentar) {
		delete(kommentar.getId());
	}

	public static void delete(int id) {
		TextbeitragMapper.delete(id);

		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM kommentar " + "WHERE id = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void removeTable() {
		Connection con = DBConnection.connection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DROP TABLE kommentar");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void createTable() {
		String sqlString = "CREATE TABLE kommentar (\n" + 
				"  id int NOT NULL,\n" + 
				"  blogeintrag int DEFAULT NULL,\n" + 
				"  PRIMARY KEY (id),\n" + 
				"  CONSTRAINT blogeintragid FOREIGN KEY (blogeintrag) REFERENCES blogeintrag (id) ON DELETE NO ACTION ON UPDATE NO ACTION\n" + 
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
