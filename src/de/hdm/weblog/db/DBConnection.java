package de.hdm.weblog.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Verwalten einer Verbindung zur Datenbank.
 * 
 * @author Thies
 */
public class DBConnection {

	private static Connection con = null;

	private static String connectionType = "mySQL";
	// "sqlServer";
	// "javaDB";

	private static String connectionUrl = "";

	static {
		switch (connectionType) {
		case "mySQL":
			connectionUrl = "jdbc:mysql://localhost/it2weblog?user=weblogdemo&password=weblogdemo";
			break;
		case "sqlServer":
			connectionUrl = "jdbc:sqlserver://edu.hdm-server.eu;user=weblogdemo;password=weblogdemo";
			break;
		case "javaDB":
			connectionUrl = "jdbc:derby:it2weblog;create=true";
			break;
		}
	}

	/**
	 * 
	 * @return DAS <code>DBConncetion</code>-Objekt.
	 * @see con
	 */
	public static Connection connection() {
		// Wenn es bisher keine Conncetion zur DB gab, ...
		if (con == null) {
			try {
				/*
				 * Falls die Driver-Klasse nicht vor dieser Klasse geladen wird,
				 * ist der entsprechende Treiber noch nicht registriert.
				 * Hiermit wird das Laden erzwungen, was zur Registrierung einer
				 * Instanz beim DriverManager f�hrt.
				 */
				switch (connectionType) {
				case "mySQL":
					Class.forName("com.mysql.jdbc.Driver");
					break;
				case "javaDB":
					Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
					break;
				case "sqlServer":
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					break;
				}
				/*
				 * Der DriverManager nimmt eine Verbindung mit den oben in der Variable url
				 * angegebenen Verbindungsinformationen auf.
				 *
				 * Diese Verbindung wird in der statischen Variable con abgespeichert und fortan
				 * verwendet.
				 */
				con = DriverManager.getConnection(connectionUrl);
			} catch (SQLException e1) {
				con = null;
				e1.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Zurueckgegeben der Verbindung
		return con;
	}
}
