package de.hdm.weblog.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.weblog.Blogeintrag;

public class BlogMapper {

	private static BlogMapper blogMapper = null;

	public BlogMapper() {
	}

	public static BlogMapper blogMapper() {
		if (blogMapper == null) {
			blogMapper = new BlogMapper();
		}
		return blogMapper;
	}

	public Vector<Blogeintrag> findAll() {
		Connection con = DBConnection.connection();
		Vector<Blogeintrag> result = new Vector<Blogeintrag>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from textbeitrag, blogeintrag "
					+ "where textbeitrag.id = blogeintrag.id " + "ORDER BY blogeintrag.id");

			while (rs.next()) {
				Blogeintrag be = new Blogeintrag(rs.getString("textbeitrag.inhalt"));

				// Blogeintrag erstellen
				be.setId(rs.getInt("textbeitrag.id"));
				be.setDatum(rs.getDate("textbeitrag.datum"));
				be.setTitel(rs.getString("blogeintrag.titel"));
				be.setUntertitel(rs.getString("blogeintrag.untertitel"));
				be.setAutor(PersonMapper.personMapper().findById(rs.getInt("textbeitrag.autor")));

				result.addElement(be);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return be;
	}

	public int add(Blogeintrag blogeintrag) {

		int id = TextbeitragMapper.textbeitragMapper().add(blogeintrag);
		if (id == 0) {
			return 0;
		} else {
			blogeintrag.setId(id);
		}

		Connection con = DBConnection.connection();
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate("INSERT INTO blogeintrag " + "(id, titel, untertitel) VALUES ("
			+ id + ", "
			+ "\"" + blogeintrag.getTitel() + "\", " 
			+ "\"" + blogeintrag.getUntertitel() + "\")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;

	}

	public void delete(Blogeintrag blogeintrag) {

		TextbeitragMapper.textbeitragMapper().delete(blogeintrag);

		Connection con = DBConnection.connection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM blogeintrag " + "WHERE id = " + blogeintrag.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
