package com.webservice.soap;

import javax.jws.WebService;

/**
 * @author Ben Yasin
 */
@WebService(name = "UserSoapService",targetNamespace = "http://webservice-rest-soap.org")
public interface UserSoapService {

    public long countUser();


}
