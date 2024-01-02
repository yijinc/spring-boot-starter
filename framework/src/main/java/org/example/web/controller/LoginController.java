package org.example.web.controller;

import jakarta.validation.constraints.NotBlank;
import org.example.domain.ResponseResult;
import org.example.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginController {

    @Autowired
    LoginService loginService;
    record UsernameLoginBody(@NotBlank(message = "用户名不能为空") String username,
                             @NotBlank(message = "密码不能为空") String password) {}
    @PostMapping("/login")
    ResponseResult<?> login(@RequestBody @Validated UsernameLoginBody body) {
        String token = loginService.login(body.username, body.password);
        return ResponseResult.ok(token);
    }

    record PhoneLoginBody(@NotBlank(message = "手机号不能为空") String phone,
                          @NotBlank(message = "验证码不能为空")String smsCode) {}
    @PostMapping("/code/login")
    ResponseResult<?> loginByCode(@RequestBody @Validated PhoneLoginBody body) {
        String token = loginService.loginByPhone(body.phone, body.smsCode);
        return ResponseResult.ok(token);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    ResponseResult<?> logout() {
        loginService.logout();
        return ResponseResult.ok();
    }

    record SMSCodeBody(@NotBlank(message = "手机号不能为空") String phone) {}
    @PostMapping("/send/smsCode")
    ResponseResult<?> loginByCode(@RequestBody @Validated SMSCodeBody body) {
        loginService.sendSmsCodeForLogin(body.phone);
        return ResponseResult.ok();
    }
}
