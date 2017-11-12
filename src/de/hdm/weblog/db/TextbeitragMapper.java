package de.hdm.weblog.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import de.hdm.weblog.Textbeitrag;

public class TextbeitragMapper {

	private static TextbeitragMapper textbeitragMapper = null;

	protected TextbeitragMapper() {
	}

	public static TextbeitragMapper textbeitragMapper() {
		if (textbeitragMapper == null) {
			textbeitragMapper = new TextbeitragMapper();
		}
		return textbeitragMapper;
	}

	public int insert(Textbeitrag textbeitrag) {
		Connection con = DBConnection.connection();

		PreparedStatement statement;
		int id = 0;
		try {
			String sqlString = "INSERT INTO textbeitrag (datum, inhalt, autor) VALUES (?, ?, ?)";
			
			statement = con.prepareStatement(sqlString, Statement.RETURN_GENERATED_KEYS);
			statement.setDate(1, new java.sql.Date(textbeitrag.getDatum().getTime()));
			statement.setString(2, textbeitrag.getInhalt());
			statement.setInt(3, textbeitrag.getAutor().getId());
			
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
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
			e.printStackTrace();
		}

	}

}
