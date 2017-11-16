package de.hdm.weblog.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.weblog.Blogeintrag;

public class BlogeintragMapper {

	private static BlogeintragMapper blogeintragMapper = null;

	protected BlogeintragMapper() {
	}

	public static BlogeintragMapper blogeintragMapper() {
		if (blogeintragMapper == null) {
			blogeintragMapper = new BlogeintragMapper();
		}
		return blogeintragMapper;
	}

	public Vector<Blogeintrag> findAll() {
		Connection con = DBConnection.connection();
		Vector<Blogeintrag> result = new Vector<Blogeintrag>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from textbeitrag, blogeintrag "
					+ "where textbeitrag.id = blogeintrag.id " + "ORDER BY textbeitrag.datum");

			while (rs.next()) {
				Blogeintrag be = new Blogeintrag(rs.getString("textbeitrag.inhalt"));

				// Blogeintrag erstellen
				be.setId(rs.getInt("textbeitrag.id"));
				be.setDatum(rs.getDate("textbeitrag.datum"));
				be.setTitel(rs.getString("blogeintrag.titel"));
				be.setUntertitel(rs.getString("blogeintrag.untertitel"));
				be.setAutor(PersonMapper.personMapper().findById(rs.getInt("textbeitrag.autor")));
				KommentarMapper.kommentarMapper().findAllForBlogeintrag(be);

				result.addElement(be);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zurueckgeben
		return result;
	}

	public Blogeintrag findById(int id) {
		Connection con = DBConnection.connection();
		Blogeintrag be = null;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from textbeitrag, blogeintrag "
					+ "where textbeitrag.id = blogeintrag.id and blogeintrag.id = " + id);

			if (rs.next()) {
				be = new Blogeintrag(rs.getString("textbeitrag.inhalt"));

				// Blogeintrag erstellen
				be.setId(rs.getInt("textbeitrag.id"));
				be.setDatum(rs.getDate("textbeitrag.datum"));
				be.setTitel(rs.getString("blogeintrag.titel"));
				be.setUntertitel(rs.getString("blogeintrag.untertitel"));
				be.setAutor(PersonMapper.personMapper().findById(rs.getInt("textbeitrag.autor")));
				KommentarMapper.kommentarMapper().findAllForBlogeintrag(be);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return be;
	}

	public void insert(Blogeintrag blogeintrag) {

		TextbeitragMapper.textbeitragMapper().insert(blogeintrag);

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
	
	public void delete(Blogeintrag blogeintrag) {
		delete(blogeintrag.getId());
	}

	public void delete(int id) {
		TextbeitragMapper.textbeitragMapper().delete(id);
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM blogeintrag " + "WHERE id = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
