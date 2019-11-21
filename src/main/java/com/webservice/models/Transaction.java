package com.webservice.models;

import java.util.Date;

public class Transaction extends Model {
    public String srcAccountNumber;
    public String destAccountNumber;
    public String destVirtualAccount = null;
   	public long amount;
    public Date createdAt = new Date();
}