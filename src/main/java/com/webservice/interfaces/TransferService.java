package com.webservice.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

@WebService
@SOAPBinding(style = Style.RPC)
public interface TransferService {
    @WebMethod
    @XmlElementWrapper
    @XmlElement(name = "return")
	String transferFund(@WebParam(name = "srcAccountNumber") String srcAccountNumber, @WebParam(name = "destAccountNumber") String destAccountNumber, @WebParam(name = "amount") long amount, @WebParam(name = "isVirtualAccount") Boolean isVirtualAccount);
}
