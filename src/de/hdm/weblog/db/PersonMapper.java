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

	public Person findById(int id) {
		Connection con = DBConnection.connection();

		Person person = null;
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM person " + "WHERE id = " + id);
			if (rs.next()) {
				person = new Person(rs.getString("nachname"), rs.getString("vorname"), rs.getString("email"));
				person.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return person;
	}

	public Person findByEmail(String email) {
		Connection con = DBConnection.connection();

		Person person = null;
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM person " + "WHERE email = \"" + email + "\"");
			if (rs.next()) {
				person = new Person(rs.getString("nachname"), rs.getString("vorname"), rs.getString("email"));
				person.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return person;
	}

	public int add(Person person) {
		Connection con = DBConnection.connection();

		// Person erstellen
		int id = 0;
		String insertTableSQL = "INSERT INTO person (nachname, vorname, email) VALUES " + "(" + "\"" + person.getName()
				+ "\", " + "\"" + person.getVorname() + "\", " + "\"" + person.getEmail() + "\")";
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

			ResultSet key = stmt.getGeneratedKeys();
			if (key.next()) {
				id = key.getInt(1);
				person.setId(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id;

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
