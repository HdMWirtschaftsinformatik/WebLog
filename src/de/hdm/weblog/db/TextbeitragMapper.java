package de.hdm.weblog.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

	public void insert(Textbeitrag textbeitrag) {
		Connection con = DBConnection.connection();

		try {
			PreparedStatement statement = con.prepareStatement(
					"INSERT INTO textbeitrag (datum, inhalt, autor) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			statement.setDate(1, new java.sql.Date(textbeitrag.getDatum().getTime()));
			statement.setString(2, textbeitrag.getInhalt());
			statement.setInt(3, textbeitrag.getAutor().getId());

			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				textbeitrag.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Textbeitrag textbeitrag) {
		delete(textbeitrag.getId());
	}
	
	public void delete(int id) {

		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM textbeitrag " + "WHERE id = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
