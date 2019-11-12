package com.webservice.models;
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;

import com.webservice.database.*;

abstract class Model {
	public static Database getMySQLConnection() {
		return new Database();
	}
	
	public static ArrayList<HashMap<String, Object>> getBy(Database connection, String column, String value, String className) throws Exception {
		String query = "SELECT * FROM " + className + " WHERE " + column + " = " + "'" + value + "'" +  ";";
		ArrayList<HashMap<String, Object>> results = connection.executeGetQuery(query);
		return results;
	}

	public static int insertIntoVirtualAccounts(Database connection, String virtualAccountNumber, String accountNumber) throws Exception {
		String query = "INSERT INTO `virtual_accounts` (`virtual_account_number`, `account_number`, `created_at`) VALUES ( \'" + virtualAccountNumber + "\' , \'" + accountNumber + "\' , now())";
		int result = connection.executeUpdateQuery(query);
		return result;
	}
}
