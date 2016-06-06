package de.hdm.weblog.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import de.hdm.weblog.Textbeitrag;

public class TextbeitragMapper {

	private static TextbeitragMapper textbeitragMapper = null;

	public TextbeitragMapper() {
	}

	public static TextbeitragMapper textbeitragMapper() {
		if (textbeitragMapper == null) {
			textbeitragMapper = new TextbeitragMapper();
		}
		return textbeitragMapper;
	}

	public int add(Textbeitrag textbeitrag) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		Connection con = DBConnection.connection();

		Statement statement;
		int id = 0;
		try {
			statement = con.createStatement();
			String sqlString = "INSERT INTO textbeitrag " + "(datum, inhalt, autor) VALUES (" 
					+ "\"" + sdf.format(textbeitrag.getDatum()) + "\", "
					+ "\"" + textbeitrag.getInhalt() + "\","
					+ textbeitrag.getAutor().getId() + ")";
			statement.executeUpdate(sqlString, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public void delete(Textbeitrag textbeitrag) {

		Connection con = DBConnection.connection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM textbeitrag " + "WHERE id = " + textbeitrag.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
