package com.webservice.database;

import java.sql.*;

public class Database {
	// Constants
	static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/users";
	static final String DB_USERNAME = "root";
	static final String DB_PASSWORD = "password";
	
	// Connection
	private Connection connection;
	private Statement statement;

	// Constructor
	public Database() {
		// Initialize new DB connection.
		initializeDB();
	}
	
	// Methods:
	public void initializeDB() {
		try {
			System.out.println("Initializing DB connection...");
			Class.forName(DB_DRIVER);
			connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			statement = connection.createStatement();
			System.out.println("Database has been connected.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String query) {
		ResultSet results = null;
		try {
			results = statement.executeQuery(query);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return results;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public Statement getStatement() {
		return statement;
	}
}
