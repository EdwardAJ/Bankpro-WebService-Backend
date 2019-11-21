package com.webservice.controllers;

import com.webservice.models.Transaction;
import com.webservice.interfaces.CheckService;

import javax.jws.WebService;
import javax.jws.WebParam;

import java.util.ArrayList;
import java.util.HashMap;

import java.sql.*;

import com.webservice.database.*;

@WebService
public class CheckController extends Controller implements CheckService {
	@Override
	public boolean checkCreditTransaction(@WebParam(name = "accountNumber") String accountNumber, @WebParam(name = "startTime") String startTime, @WebParam(name = "endTime") String endTime, @WebParam(name = "amount") long amount) {
        boolean isValidated = false;
        try {
			ArrayList<HashMap<String, Object>> results = Transaction.getCreditTransaction(accountNumber, startTime, endTime, amount);
			// If account number exists in database
			if (results.size() > 0) {
                isValidated = true;
            }
		} catch (Exception e) {
            System.out.println(e);
            return false;
        }
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
