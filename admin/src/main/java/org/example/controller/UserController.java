package org.example.controller;

import org.example.domain.ResponseResult;
import org.example.service.MyCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    MyCustomerService myCustomerService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    ResponseResult<String> hello(Authentication authentication) {
        return ResponseResult.ok("hello, " + authentication.getName() + "!");
    }

    @RequestMapping(value = "/user/hello", method = RequestMethod.GET)
    ResponseResult<String> userHello(Authentication authentication) {
        return ResponseResult.ok("hello, " + authentication.getName() + "! you has USER role");
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    ResponseResult<String> home() {
        return ResponseResult.ok("home");
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    ResponseResult<String> custom() {
        return ResponseResult.ok(myCustomerService.getAccount());
    }
}
