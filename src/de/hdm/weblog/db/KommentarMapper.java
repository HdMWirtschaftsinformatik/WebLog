package de.hdm.weblog.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.weblog.Blogeintrag;
import de.hdm.weblog.Kommentar;

public class KommentarMapper {

	private static KommentarMapper kommentarMapper = null;

	public KommentarMapper() {
	}

	public static KommentarMapper kommentarMapper() {
		if (kommentarMapper == null) {
			kommentarMapper = new KommentarMapper();
		}
		return kommentarMapper;
	}

	public Vector<Kommentar> findAll() {
		Connection con = DBConnection.connection();
		Vector<Kommentar> result = new Vector<Kommentar>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from textbeitrag, kommentar "
					+ "where textbeitrag.id = kommentar.id " + "ORDER BY kommentar.id");

			while (rs.next()) {
				Kommentar kom = new Kommentar(rs.getString("textbeitrag.inhalt"));

				// Blogeintrag erstellen
				kom.setId(rs.getInt("textbeitrag.id"));
				kom.setDatum(rs.getDate("textbeitrag.datum"));
				kom.setAutor(PersonMapper.personMapper().findById(rs.getInt("textbeitrag.autor")));
				kom.setBeitrag(BlogMapper.blogMapper().findById(rs.getInt("kommentar.blogeintrag")));

				result.addElement(kom);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Ergebnisvektor zurueckgeben
		return result;
	}

	public Kommentar findById(int id) {
		Connection con = DBConnection.connection();

		Statement stmt;
		Kommentar kom = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from textbeitrag, kommentar "
					+ "where textbeitrag.id = kommentar.id and kommentar.id = " + id);

			if (rs.next()) {
				kom = new Kommentar(rs.getString("textbeitrag.inhalt"));

				// Kommentar erstellen
				kom.setId(rs.getInt("textbeitrag.id"));
				kom.setDatum(rs.getDate("textbeitrag.datum"));
				kom.setAutor(PersonMapper.personMapper().findById(rs.getInt("textbeitrag.autor")));
				kom.setBeitrag(BlogMapper.blogMapper().findById(rs.getInt("kommentar.blogbeitrag")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kom;
	}
	
	public Vector<Kommentar> findAllForBlogeintrag(Blogeintrag be) {
		Connection con = DBConnection.connection();
		Vector<Kommentar> result = new Vector<Kommentar>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from textbeitrag, kommentar "
					+ "where textbeitrag.id = kommentar.id and kommentar.blogeintrag = "
					+ be.getId());

			while (rs.next()) {
				Kommentar kom = new Kommentar(rs.getString("textbeitrag.inhalt"));

				// Blogeintrag erstellen
				kom.setId(rs.getInt("textbeitrag.id"));
				kom.setDatum(rs.getDate("textbeitrag.datum"));
				kom.setAutor(PersonMapper.personMapper().findById(rs.getInt("textbeitrag.autor")));
				kom.setBeitrag(BlogMapper.blogMapper().findById(rs.getInt("kommentar.blogeintrag")));

				result.addElement(kom);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Ergebnisvektor zurueckgeben
		return result;
	}

	public int add(Kommentar kommentar) {

		int id = TextbeitragMapper.textbeitragMapper().add(kommentar);
		if (id == 0) {
			return 0;
		} else {
			kommentar.setId(id);
		}

		Connection con = DBConnection.connection();
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate("INSERT INTO kommentar (id, blogeintrag) VALUES (" + id + ","
					+ kommentar.getBeitrag().getId() + ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;

	}

	public void delete(Kommentar kommentar) {

		TextbeitragMapper.textbeitragMapper().delete(kommentar);

		Connection con = DBConnection.connection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM kommentar " + "WHERE id = " + kommentar.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
