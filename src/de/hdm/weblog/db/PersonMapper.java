package de.hdm.weblog.db;

import java.sql.*;

import de.hdm.weblog.Person;

public class PersonMapper {

	private static PersonMapper personMapper = null;

	protected PersonMapper() {
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM person " + "WHERE email = \'" + email + "\'");
			if (rs.next()) {
				person = new Person(rs.getString("nachname"), rs.getString("vorname"), rs.getString("email"));
				person.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}

	public void insert(Person person) {
		Connection con = DBConnection.connection();

		try {
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO person (nachname, vorname, email) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, person.getName());
			stmt.setString(2, person.getVorname());
			stmt.setString(3, person.getEmail());
			stmt.executeUpdate();

			ResultSet key = stmt.getGeneratedKeys();
			if (key.next()) {
				person.setId(key.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {

		Connection con = DBConnection.connection();

		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM person " + "WHERE id = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void delete(Person person) {
		delete(person.getId());
	}

}
