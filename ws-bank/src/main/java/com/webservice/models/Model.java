package com.webservice.models;
import java.sql.*;

import com.webservice.database.*;

abstract class Model {
	protected static Database getMySQLConnection() {
		return new Database();
	}
	
	public static ResultSet getBy(String column, String value, String className) {
		Database connection = getMySQLConnection();
		String query = "SELECT * FROM " + className + " WHERE " + column + " = " + value;
		ResultSet results = connection.executeQuery(query);
		return results;
	}
}
