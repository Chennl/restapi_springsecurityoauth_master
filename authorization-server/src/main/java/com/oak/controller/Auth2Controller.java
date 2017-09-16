package com.oak.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class Auth2Controller {
    private static final Logger logger = LoggerFactory.getLogger(Auth2Controller.class);
    @RequestMapping("/user")
    public Principal user(Principal user) {
        logger.info("AS /user has been called");
        logger.debug("user info: " + user.toString());
        return user;
    }
}
