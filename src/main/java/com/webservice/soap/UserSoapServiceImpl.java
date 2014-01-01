package com.webservice.soap;

import com.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.jws.WebService;

/**
 * @author Ben Yasin
 */
@WebService(
        portName = "UserSoapPort",
        serviceName = "UserSoapService",
        targetNamespace = "http://webservice-rest-soap.org",
        endpointInterface = "com.webservice.soap.UserSoapService")
public class UserSoapServiceImpl implements UserSoapService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSoapServiceImpl.class);

    @Resource
    private UserService userService;

    @Override
    public long countUser() {
        long result = userService.countUser();
        LOGGER.info("count user result is :" + result);
        return result;
    }
}
