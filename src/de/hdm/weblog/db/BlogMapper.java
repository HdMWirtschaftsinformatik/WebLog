package de.hdm.weblog.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.weblog.Blogeintrag;
import de.hdm.weblog.Person;
import de.hdm.weblog.Textbeitrag;

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

	public Vector<Blogeintrag> findAll() throws SQLException {
		Connection con = DBConnection.connection();
		Vector<Blogeintrag> result = new Vector<Blogeintrag>();

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from textbeitrag, blogeintrag "
				+ "where textbeitrag.id = blogeintrag.id " + "ORDER BY blogeintrag.id");

		while (rs.next()) {
			Blogeintrag be = new Blogeintrag(rs.getString("inhalt"));

			// Blogeintrag erstellen
			be.setId(rs.getInt("id"));
			be.setDatum(rs.getDate("datum"));
			be.setTitel(rs.getString("titel"));
			be.setUntertitel(rs.getString("untertitel"));
			be.setAutor(PersonMapper.personMapper().findById(rs.getInt("autor")));

			result.addElement(be);
		}

		// Ergebnisvektor zurueckgeben
		return result;
	}

	public Blogeintrag findById(int id) {
		Connection con = DBConnection.connection();

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from textbeitrag, blogeintrag "
				+ "where textbeitrag.id = blogeintrag.id and blogeintrag.id = " + id);

		if (rs.next()) {
			Blogeintrag be = new Blogeintrag(rs.getString("inhalt"));

			// Blogeintrag erstellen
			be.setId(rs.getInt("id"));
			be.setDatum(rs.getDate("datum"));
			be.setTitel(rs.getString("titel"));
			be.setUntertitel(rs.getString("untertitel"));
			be.setAutor(PersonMapper.personMapper().findById(rs.getInt("autor")));

			return be;
		} else {
			return null;
		}
	}

	public void add(Blogeintrag blogeintrag) {

		Connection con = DBConnection.connection();

		// Textbeitrag erstellen

		String insertTableSQL = "INSERT INTO textbeitrag " + "(datum, inhalt, fk_person) VALUES " + "(?,?,?)";

		PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setDate(1, blogeintrag.textbeitrag.getDatum());
		preparedStatement.setString(2, blogeintrag.textbeitrag.getInhalt());
		preparedStatement.setInt(3, blogeintrag.person.getId());

		// execute insert SQL stetement
		preparedStatement.executeUpdate();

		ResultSet key = preparedStatement.getGeneratedKeys();
		key.next();
		int id = key.getInt(1);

		// Blogeintrag erstellen

		insertTableSQL = "INSERT INTO blogeintrag " + "(id, titel, untertitel) VALUES " + "(?,?,?)";
		preparedStatement = con.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setInt(1, id);
		preparedStatement.setString(2, blogeintrag.getTitel());
		preparedStatement.setString(3, blogeintrag.getUntertitel());

		// execute insert SQL stetement
		preparedStatement.executeUpdate();

		key = preparedStatement.getGeneratedKeys();
		key.next();
		id = key.getInt(1);

	}

	public void delete(int id) throws SQLException {

		Connection con = DBConnection.connection();

		Statement stmt = con.createStatement();

		String sql = "DELETE FROM blogeintrag " + "WHERE id = " + id;

		stmt.executeUpdate(sql);

	}

	// public addKommentar (Kommentar kommentar){}

}
