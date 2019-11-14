package com.webservice.models;
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.lang.reflect.*;

import com.webservice.database.*;

abstract class Model {
	private static Database dbConnection = new Database();

    public static Database getMySQLConnection() {
		return new Database();
    }
	
	public static ArrayList<HashMap<String, Object>> getBy(String column, String value, String relationName) throws Exception {
		String query = "SELECT * FROM " + relationName + " WHERE " + column + " = " + "'" + value + "'" +  ";";
		ArrayList<HashMap<String, Object>> results = dbConnection.executeGetQuery(query);
		return results;
	}

	public static int insertIntoVirtualAccounts(String virtualAccountNumber, String accountNumber) throws Exception {
		String query = "INSERT INTO `virtual_accounts` (`virtual_account_number`, `account_number`, `created_at`) VALUES ( \'" + virtualAccountNumber + "\' , \'" + accountNumber + "\' , now())";
		int result = dbConnection.executeUpdateQuery(query);
		return result;
	}

	public static int insert(Object obj) throws Exception {
		String query = "INSERT INTO `";
		query += getObjectRelationName(obj);
		query += "` ";
		query += getObjectAttributesForQuery(obj);
		query += " VALUES ";
		query += getObjectAttributeValuesForQuery(obj);
		// throw new Exception(query);
		int result = dbConnection.executeUpdateQuery(query);
		return result;
	}

	private static String getObjectRelationName(Object obj) {
		String className = obj.getClass().getSimpleName();
		className = className.replaceAll("([^_A-Z])([A-Z])", "$1_$2").toLowerCase();
		className += "s";
		return className;
	}

	private static String getObjectAttributesForQuery(Object obj) {
		String query = "(";
		Class<?> objectClass = obj.getClass();
		Field[] attributes = objectClass.getFields();
		for (int i = 0; i < attributes.length; i++) {
			if (attributes[i].getName() == "id") {
				continue;
			}
			query += "`";
			query += attributes[i].getName().replaceAll("([^_A-Z])([A-Z])", "$1_$2").toLowerCase();
			query += "`";
			if (i < attributes.length - 1) {
				query += ", ";
			}
		}
		query += ")";
		return query;
	}

	private static String getObjectAttributeValuesForQuery(Object obj) {
		String query = "(";
		Class<?> objectClass = obj.getClass();
		Field[] attributes = objectClass.getFields();
		for (int i = 0; i < attributes.length; i++) {
			if (attributes[i].getName() == "id") {
				continue;
			}
			try {
				if (needQuote(attributes[i].get(obj))) {
					query += "\'";
				}
				if (attributes[i].get(obj) != null) {
					if (attributes[i].get(obj).getClass().getName() == "java.util.Date") {
						query += new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(attributes[i].get(obj));
					} else {
						query += attributes[i].get(obj);
					}
				} else {
					query += "NULL";
				}
				if (needQuote(attributes[i].get(obj))) {
					query += "\'";
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			if (i < attributes.length - 1) {
				query += ", ";
			}
		}
		query += ")";
		return query;
	}

	private static Boolean needQuote(Object obj) {
		if (obj == null) {
			return false;
		}
		return obj.getClass().getName() == "java.lang.String" || obj.getClass().getName() == "java.util.Date";
	}
}
