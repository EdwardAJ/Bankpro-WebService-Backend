package com.webservice.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface VirtualAccountService {
	@WebMethod
	public String generateVirtualAccount(@WebParam(name = "accountNumber") String accountNumber);
}
