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
            return ResponseResult.fail(e.getMessage());
        }
    }

    record PhoneLoginBody(String phone, String smsCode) {}
    @PostMapping("/code/login")
    ResponseResult<?> loginByCode(@RequestBody PhoneLoginBody body) {
        try {
            String token = loginService.loginByPhone(body.phone, body.smsCode);
            return ResponseResult.ok(token);
        } catch (Exception e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    ResponseResult<?> logout() {
        loginService.logout();
        return ResponseResult.ok();
    }

    record SMSCodeBody(String phone) {}
    @PostMapping("/send/smsCode")
    ResponseResult<?> loginByCode(@RequestBody SMSCodeBody body) {
        try {
            loginService.sendSmsCodeForLogin(body.phone);
            return ResponseResult.ok();
        } catch (Exception e) {
            return ResponseResult.fail(e.getMessage());
        }
    }
}
