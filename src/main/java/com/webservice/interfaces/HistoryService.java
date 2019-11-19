package com.webservice.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import java.util.ArrayList;

@WebService
@SOAPBinding(style = Style.RPC)
public interface HistoryService {
    @WebMethod
    ArrayList<String> getHistory(@WebParam(name = "accountNumber") String accountNumber);
}
