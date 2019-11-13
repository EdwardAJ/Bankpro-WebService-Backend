package com.webservice.controllers;

import com.webservice.models.VirtualAccount;
import com.webservice.interfaces.VirtualAccountService;
import javax.jws.WebService;
import javax.jws.WebParam;

import java.util.ArrayList;
import java.util.HashMap;

import java.sql.*;

import com.webservice.database.*;
import com.webservice.utils.*;


@WebService
public class VirtualAccountController extends Controller implements VirtualAccountService {
    @Override
    public String generateVirtualAccount(@WebParam(name = "accountNumber") String accountNumber) {
        int suffix = 1;
        String virtualAccountNumber = null;
        Database connection = VirtualAccount.getMySQLConnection();
        try {
            // Get virtual_account of latest transaction
			ArrayList<HashMap<String, Object>> fetchResults = VirtualAccount.getBy(connection, "account_number", accountNumber, "virtual_accounts");
            for (int row = 0; row < fetchResults.size(); row++) {
                suffix++;
            }
            // Now insert to transactions
            int result = VirtualAccount.insertIntoVirtualAccounts(connection, accountNumber + String.valueOf(suffix), accountNumber);
            // Only one row should be changed
            if (result == 1) {
                virtualAccountNumber = accountNumber + String.valueOf(suffix);   
            }
		} catch (Exception e) {
			System.out.println(e);
		}
        connection.closeConnection();
        return virtualAccountNumber;
    }


    public static void main (String[] args) {
        // int suffix = 1;
        // String accountNumber = "13517000";
        // String virtualAccountNumber = null;
        // Database connection = VirtualAccount.getMySQLConnection();
        // try {
        //     // Get virtual_account of latest transaction
		// 	ArrayList<HashMap<String, Object>> fetchResults = VirtualAccount.getBy(connection, "account_number", accountNumber, "virtual_accounts");
        //     for (int row = 0; row < fetchResults.size(); row++) {
        //         suffix++;
        //     }
        //     // Now insert to transactions
        //     int result = VirtualAccount.insertIntoVirtualAccounts(connection, accountNumber + String.valueOf(suffix), accountNumber);
        //     if (result == 1) {
        //         virtualAccountNumber = accountNumber + String.valueOf(suffix);   
        //     }
		// } catch (Exception e) {
		// 	System.out.println(e);
		// }
        // connection.closeConnection();
        // System.out.println(virtualAccountNumber);
    }
}