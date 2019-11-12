package com.webservice.database;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
	// Constants
	static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/bank";
	static final String DB_USERNAME = "root";
	static final String DB_PASSWORD = "root";
	
	// Connection
	private Connection connection = null;
	private Statement statement = null;

	// Constructor
	public Database() {
		// Initialize new DB connection.
		initializeDB();
	}
	
	// Methods:
	public void initializeDB() {
		try {
			// System.out.println("Initializing DB connection...");
			Class.forName(DB_DRIVER);
			connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			// System.out.println("Database has been connected.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList executeGetQuery(String query) throws Exception {
		ArrayList results = null;
		ResultSet tempResults = null;
		// Initialize
		statement = connection.createStatement();
		tempResults = statement.executeQuery(query);
		// Convert ResultSet to HashMap
		results = convertResultSet(tempResults);
		// Close resultSet and statement
		tempResults.close();
		closeStatement();
		return results;
	}
	
	// Convert resultSet to List. The purpose is to close resultSet and statement
	public ArrayList convertResultSet(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		// Setting the initial size of ArrayList reduces the number of re-allocation
		ArrayList<Object> results = new ArrayList<Object>(100);
		HashMap<String, Object> row = new HashMap<String, Object>(columns);
		while (rs.next()) {
			for (int col = 1; col <= columns; ++col ) {
				row.put(md.getColumnName(col), rs.getObject(col));
			}
			results.add(row);
		}
		return results;
	}

	public void closeStatement() {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}
	
	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public Statement getStatement() {
		return statement;
	}
}
