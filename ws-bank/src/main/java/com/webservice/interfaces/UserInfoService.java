package com.webservice.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import java.util.ArrayList;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

@WebService
@SOAPBinding(style = Style.RPC)
public interface UserInfoService {
    @WebMethod
    @XmlElementWrapper
    @XmlElement(name = "return")
	ArrayList<String> fetchUserInfo(@WebParam(name = "accountNumber") String accountNumber);
}
