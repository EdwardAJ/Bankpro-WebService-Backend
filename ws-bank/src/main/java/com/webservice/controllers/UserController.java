package com.webservice.controllers;

import javax.jws.WebService;
import com.webservice.models.User;
import com.webservice.interfaces.UserService;

@WebService
public class UserController extends Controller implements UserService {
	@Override
	public boolean validateAccountNumber(String accountNumber) {
		if (User.getBy("account_number", accountNumber, "User") != null) {
			return true;
		} else {
			return false;
		}
	}
}
