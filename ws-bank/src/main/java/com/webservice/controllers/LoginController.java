package com.webservice.controllers;

import com.webservice.models.User;
import com.webservice.interfaces.UserService;
import javax.jws.WebService;
import javax.jws.WebParam;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import java.sql.*;

import com.webservice.database.*;

@WebService
public class LoginController extends Controller implements UserService {
	@Override
	public boolean validateAccountNumber(@WebParam(name = "accountNumber") String accountNumber) {
		Database connection = User.getMySQLConnection();
		boolean isValidated = false;
		try {
			ArrayList results = User.getBy(connection, "account_number", accountNumber, "users");
			if (results.size() > 0) {
				isValidated = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		connection.closeConnection();
		return isValidated;
	}

	public static void main(String[] args) {
		Database connection = User.getMySQLConnection();
		boolean isValidated = false;
		try {
			ArrayList results = User.getBy(connection, "account_number", "13517115", "users");
			for (int i = 0; i < results.size(); i++) {
				System.out.println(results.get(i));
				Object row = results.get(i);
				// System.out.println(row.name);
			}
			if (results.size() > 0) {
				isValidated = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		connection.closeConnection();
	}
}
