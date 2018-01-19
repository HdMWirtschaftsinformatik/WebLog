package de.hdm.weblog.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.weblog.shared.Person;

public class PersonMapper {

	public static Person findById(int id) {
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

	public static Person findByEmail(String email) {
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

	public static Vector<Person> findAll() {
		Connection con = DBConnection.connection();

		Vector<Person> persons = new Vector<Person>();
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM person");
			Person person;
			while (rs.next()) {
				person = new Person(rs.getString("nachname"), rs.getString("vorname"), rs.getString("email"));
				person.setId(rs.getInt("id"));
				persons.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return persons;
	}

	public static void insert(Person person) {
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

	public static void delete(int id) {

		Connection con = DBConnection.connection();

		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM person " + "WHERE id = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void delete(Person person) {
		delete(person.getId());
	}
	
	public static void removeTable() {
		Connection con = DBConnection.connection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DROP TABLE person");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void createTable() {
		String sqlString = "CREATE TABLE person (\n" + 
				"  id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n" + 
				"  vorname varchar(45) DEFAULT NULL,\n" + 
				"  nachname varchar(45) DEFAULT NULL,\n" + 
				"  email varchar(45) DEFAULT NULL,\n" + 
				"  PRIMARY KEY (id)\n" +  
				" )";
		Connection con = DBConnection.connection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sqlString);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

}
