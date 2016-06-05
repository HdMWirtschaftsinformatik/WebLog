package de.hdm.weblog.db;

import java.sql.*;

import de.hdm.weblog.Person;

public class PersonMapper {

	private static PersonMapper personMapper = null;

	public PersonMapper() {
	}

	public static PersonMapper personMapper() {
		if (personMapper == null) {
			personMapper = new PersonMapper();
		}
		return personMapper;
	}

	public Person checkIfExists(Person person) throws SQLException {

		String asd = "adfsf \"";

		Connection con = DBConnection.connection();

		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT id FROM person " + "WHERE email = \"" + person.getEmail() + "\"");

		if (rs.first() == false) {
			return null;
		}

		else {

			rs.first();
			person.setId(rs.getInt("id"));
			return person;
		}

	}

	public Person add(Person person) {
		Connection con = DBConnection.connection();

		// Person erstellen

		String insertTableSQL = "INSERT INTO person " + "(name, vorname, email) VALUES " + "(?,?,?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, person.getName());
			preparedStatement.setString(2, person.getVorname());
			preparedStatement.setString(3, person.getEmail());

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

			ResultSet key = preparedStatement.getGeneratedKeys();
			key.next();
			int id = key.getInt(1);
			person.setId(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return person;

	}

	public void delete(int id) {

		Connection con = DBConnection.connection();

		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM person " + "WHERE id = " + id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
