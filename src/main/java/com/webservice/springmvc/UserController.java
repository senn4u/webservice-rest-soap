package com.webservice.springmvc;

import com.model.User;
import com.service.UserService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* @author Ben Yasin
*/
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static User user;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping( value = "/{id}", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
    public @ResponseBody String findById(@PathVariable ObjectId id) {
        user = userService.findById(id);
        return "mvc";
    }
}
