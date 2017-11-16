package de.hdm.weblog.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.hdm.weblog.Blogeintrag;

public class KommentarMapper {

	private static KommentarMapper kommentarMapper = null;

	protected KommentarMapper() {
	}

	public static KommentarMapper kommentarMapper() {
		if (kommentarMapper == null) {
			kommentarMapper = new KommentarMapper();
		}
		return kommentarMapper;
	}

	public void findAllForBlogeintrag(Blogeintrag be) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from textbeitrag, kommentar "
					+ "where textbeitrag.id = kommentar.id and kommentar.blogeintrag = " + be.getId());

			while (rs.next()) {
				Blogeintrag.Kommentar kom = be.createKommentar(rs.getString("textbeitrag.inhalt"),
						PersonMapper.personMapper().findById(rs.getInt("textbeitrag.autor")),
						rs.getDate("textbeitrag.datum"));
				kom.setId(rs.getInt("textbeitrag.id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(Blogeintrag.Kommentar kommentar) {

		TextbeitragMapper.textbeitragMapper().insert(kommentar);
		Connection con = DBConnection.connection();
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate("INSERT INTO kommentar (id, blogeintrag) VALUES (" + kommentar.getId() + ", "
					+ kommentar.getBlockeintrag().getId() + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(Blogeintrag.Kommentar kommentar) {
		delete(kommentar.getId());
	}

	public void delete(int id) {
		TextbeitragMapper.textbeitragMapper().delete(id);

		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM kommentar " + "WHERE id = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
