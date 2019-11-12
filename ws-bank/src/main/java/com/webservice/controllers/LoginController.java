package com.webservice.controllers;

import com.webservice.models.User;
import com.webservice.interfaces.LoginService;
import javax.jws.WebService;
import javax.jws.WebParam;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import java.sql.*;

import com.webservice.database.*;

@WebService
public class LoginController extends Controller implements LoginService {
	@Override
	public boolean validateAccountNumber(@WebParam(name = "accountNumber") String accountNumber) {
		Database connection = User.getMySQLConnection();
		boolean isValidated = false;
		try {
			ArrayList<HashMap<String, Object>> results = User.getBy(connection, "account_number", accountNumber, "users");
			// If account number exists in database
			if (results.size() > 0) {
				isValidated = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		// Do not forget to close the connection
		connection.closeConnection();
		return isValidated;
	}

	public static void main(String[] args) {
		// Database connection = User.getMySQLConnection();
		// boolean isValidated = false;
		// try {
		// 	ArrayList<HashMap<String, Object>> results = User.getBy(connection, "account_number", "13517115", "users");
		// 	for (int row = 0; row < results.size(); row++) {
		// 		System.out.println(results.get(row));
		// 		System.out.println(results.get(row).get("name"));
		// 	}
		// 	if (results.size() > 0) {
		// 		isValidated = true;
		// 	}
		// } catch (Exception e) {
		// 	System.out.println(e);
		// }
		// connection.closeConnection();
	}
}
