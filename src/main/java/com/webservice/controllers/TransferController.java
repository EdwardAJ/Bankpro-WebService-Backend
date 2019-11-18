package com.webservice.controllers;

import com.webservice.models.*;
import com.webservice.interfaces.TransferService;
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
public class TransferController extends Controller implements TransferService {
    @Override
    @XmlElementWrapper 
    @XmlElement(name = "return")
    public String transferFund(@WebParam(name = "srcAccountNumber") String srcAccountNumber, @WebParam(name = "destAccountNumber") String destAccountNumber, @WebParam(name = "amount") long amount, @WebParam(name = "isVirtualAccount") Boolean isVirtualAccount) {
        ArrayList<String> info = new ArrayList<String>();
        try {
            long balanceOfTheForce = Utils.fetchUserAmount(srcAccountNumber);
            if (balanceOfTheForce < amount) {
                return "Saldo kurang!";
            }

            String virtualAccountNumber = null;
			if (!isVirtualAccount) {
                ArrayList<HashMap<String, Object>> results = User.getBy("account_number", destAccountNumber, "users");
                // If account number exists in database
                if (results.size() <= 0) {
                    return "Tujuan tidak ditemukan!";
                }
            } else {
                ArrayList<HashMap<String, Object>> results = VirtualAccount.getBy("virtual_account_number", destAccountNumber, "virtual_accounts");
                if (results.size() <= 0) {
                    return "Tujuan tidak ditemukan!";
                }

                virtualAccountNumber = destAccountNumber;
                destAccountNumber = (String) results.get(0).get("account_number");
            }

            if (srcAccountNumber.equals(destAccountNumber)) {
                return "Nomor rekening tidak boleh sama! Jangan coba-coba :(";
            }

            Transaction newTransaction = new Transaction();
            newTransaction.srcAccountNumber = srcAccountNumber;
            newTransaction.destAccountNumber = destAccountNumber;
            if (isVirtualAccount) {
                newTransaction.destVirtualAccount = virtualAccountNumber;
            }
            newTransaction.amount = amount;

			Transaction.insert(newTransaction);
		} catch (Exception e) {
			System.out.println(e);
		}
        return "Berhasil!";
    }
}