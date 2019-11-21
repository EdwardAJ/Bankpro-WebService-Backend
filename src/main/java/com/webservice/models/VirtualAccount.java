package com.webservice.models;

import java.util.Date;

public class VirtualAccount extends Model {
    public String virtualAccountNumber;
    public String accountNumber;
    public Date createdAt = new Date();
}
