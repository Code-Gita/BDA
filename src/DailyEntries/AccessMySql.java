package DailyEntries;

import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;

public class AccessMySql {

	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	final private String host = "localhost";
	final private String user = "root";
	final private String passwd = "Prashi11!";

	public Connection connectDB() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Setup the connection with the DB
			connect = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/bhatnagar_dental_art?" + "user=" + user + "&password=" + passwd);

			return connect;
		} catch (Exception e) {
			throw e;
		}

	}

	public void readDataBase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Setup the connection with the DB
			connect = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/bhatnagar_dental_art?" + "user=" + user + "&password=" + passwd);

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}
