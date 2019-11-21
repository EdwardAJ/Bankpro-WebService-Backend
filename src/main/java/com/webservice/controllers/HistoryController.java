package com.webservice.controllers;

import com.webservice.models.Transaction;
import com.webservice.interfaces.HistoryService;
import javax.jws.WebService;
import javax.jws.WebParam;

import java.util.ArrayList;
import java.util.HashMap;

import java.sql.*;

import com.webservice.database.*;
import com.webservice.utils.*;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

@WebService
public class HistoryController extends Controller implements HistoryService {
    @Override
    @XmlElementWrapper 
    @XmlElement(name = "return")
    public ArrayList<String> getHistory(@WebParam(name = "accountNumber") String accountNumber) {
        // Returns list of history's information
        ArrayList<String> histories = new ArrayList<String>();
        try {
            // Get Debit first
			ArrayList<HashMap<String, Object>> results = Transaction.getBy("src_account_number", accountNumber, "transactions");
            for (int row = results.size() - 1; row >= 0; row--) {
                // Check for virtual account.
                if (results.get(row).get("dest_virtual_account") == null) {
                    histories.add(results.get(row).get("dest_account_number").toString());
                } else {
                    histories.add(results.get(row).get("dest_virtual_account").toString());
                }
                histories.add(results.get(row).get("amount").toString());
                histories.add("Debit");
                histories.add(results.get(row).get("created_at").toString());
            }
            // Then get Credit
            ArrayList<HashMap<String, Object>> resultsCredit = Transaction.getBy("dest_account_number", accountNumber, "transactions");
            for (int row = resultsCredit.size() - 1; row >= 0; row--) {
                // Check for virtual account.
                histories.add(resultsCredit.get(row).get("src_account_number").toString());
                histories.add(resultsCredit.get(row).get("amount").toString());
                histories.add("Kredit");
                histories.add(resultsCredit.get(row).get("created_at").toString());
            }
		} catch (Exception e) {
			System.out.println(e);
		}
        return histories;
    }

    public static void main(String[] args) {
        // System.out.println(Utils.fetchAmountDecrease("13517115"));
        // System.out.println(Utils.fetchAmountIncrease("13517115"));
        // System.out.println("Final amount = " + Utils.fetchUserAmount("13517115"));
	}
}