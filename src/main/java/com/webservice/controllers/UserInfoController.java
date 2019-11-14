package com.webservice.controllers;

import com.webservice.models.User;
import com.webservice.interfaces.UserInfoService;
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
public class UserInfoController extends Controller implements UserInfoService {
    @Override
    @XmlElementWrapper 
    @XmlElement(name = "return")
    public ArrayList<String> fetchUserInfo(@WebParam(name = "accountNumber") String accountNumber) {
        // Returns list of user's information
        ArrayList<String> info = new ArrayList<String>();
        try {
			ArrayList<HashMap<String, Object>> results = User.getBy("account_number", accountNumber, "users");
            for (int row = 0; row < results.size(); row++) {
                info.add(results.get(row).get("name").toString());
                info.add(results.get(row).get("account_number").toString());
                info.add("Bank Engima");
                // Convert fetchUserAmount to string
                info.add("" + Utils.fetchUserAmount(accountNumber));
            }
		} catch (Exception e) {
			System.out.println(e);
		}
        return info;
    }

    public static void main(String[] args) {
        // System.out.println(Utils.fetchAmountDecrease("13517115"));
        // System.out.println(Utils.fetchAmountIncrease("13517115"));
        // System.out.println("Final amount = " + Utils.fetchUserAmount("13517115"));
	}
}