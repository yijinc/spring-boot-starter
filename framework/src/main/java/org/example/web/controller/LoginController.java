package org.example.web.controller;

import org.example.domain.ResponseResult;
import org.example.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginController {

    @Autowired
    LoginService loginService;
    record UsernameLoginBody(String username, String password) {}
    @PostMapping("/login")
    ResponseResult<?> login(@RequestBody UsernameLoginBody body) {
        try {
            String token = loginService.login(body.username, body.password);
            return ResponseResult.ok(token);
        } catch (Exception e) {
            return ResponseResult.fail(411, e.getMessage());
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    ResponseResult<?> logout() {
        loginService.logout();
        return ResponseResult.ok("退出成功");
    }
}
