package com.webservice.utils;

import com.webservice.models.Transaction;

import java.util.ArrayList;
import java.util.HashMap;

import java.sql.*;

import com.webservice.database.*;

public final class Utils {

    private Utils() {
        
    }

    public static long fetchUserAmount(String accountNumber) {
        // Initial amount
        long amount = 100000;
        long amountIncrease = 0;
        long amountDecrease = 0;
        amountDecrease = fetchAmountDecrease(accountNumber);
        amountIncrease = fetchAmountIncrease(accountNumber);
        amount = amount + amountIncrease - amountDecrease;
        return amount;
    }

    public static long fetchAmountDecrease(String accountNumber) {
        long finalAmountDecrease = 0;
        try {
			ArrayList<HashMap<String, Object>> results = Transaction.getBy("src_account_number", accountNumber, "transactions");
            for (int row = 0; row < results.size(); row++) {
                Object amountObj = new Object();
                amountObj = results.get(row).get("amount");
                // Convert to Long
                long amountDecrease = (long) Long.parseLong(amountObj.toString());
                finalAmountDecrease += amountDecrease;
            }
		} catch (Exception e) {
			System.out.println(e);
        }
        return finalAmountDecrease;
    }

    public static long fetchAmountIncrease(String accountNumber) {
        long finalAmountIncrease = 0;
        try {
			ArrayList<HashMap<String, Object>> results = Transaction.getBy("dest_account_number", accountNumber, "transactions");
            for (int row = 0; row < results.size(); row++) {
                Object amountObj = new Object();
                amountObj = results.get(row).get("amount");
                // Convert to Long
                long amountIncrease = (long) Long.parseLong(amountObj.toString());
                finalAmountIncrease += amountIncrease;
            }
		} catch (Exception e) {
			System.out.println(e);
        }
        return finalAmountIncrease;
    }
}