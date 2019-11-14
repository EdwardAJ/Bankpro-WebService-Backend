package com.webservice.models;

import java.util.Date;

public class User extends Model {
	public String id;
	public String accountNumber;
	public String name;
	public Date createdAt = new Date();
}
