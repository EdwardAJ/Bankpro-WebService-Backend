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
public interface CheckService {
    @WebMethod
    @XmlElementWrapper
    @XmlElement(name = "return")
    boolean checkCreditTransaction(@WebParam(name = "accountNumber") String accountNumber, @WebParam(name = "startTime") String startTime, @WebParam(name = "endTime") String endTime);
}
