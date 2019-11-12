package com.webservice.models;
import java.sql.*;
import java.util.ArrayList;

import com.webservice.database.*;

abstract class Model {
	public static Database getMySQLConnection() {
		return new Database();
	}
	
	public static ArrayList getBy(Database connection, String column, String value, String className) throws Exception {
		String query = "SELECT * FROM " + className + " WHERE " + column + " = " + "'" + value + "'" +  ";";
		ArrayList results = connection.executeGetQuery(query);
		return results;
	}
}
