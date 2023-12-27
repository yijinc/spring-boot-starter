package org.example.web.service;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.entity.User;
import org.example.domain.model.LoginUser;
import org.example.mapper.LoginUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class LoginService {

    /**
     * 当采用@Autowired时，根据类型注入，注入了 RedisTemplate<String, String> Bean
     * 当使用@Resource时，根据名称注入，注入了 RedisTemplate<Object, Object> Bean
     * 需要指定KeySerializer redisTemplate.setKeySerializer(new StringRedisSerializer())
     * **/
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SmsCodeService smsCodeService;

    @Autowired
    private LoginUserMapper loginUserMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String login(String username, String password) {
        if (username == null || username.isBlank()) {
            throw new RuntimeException("用户名不能为空");
        }
        if (password == null || password.isBlank()) {
            throw new RuntimeException("密码不能为空");
        }
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (authentication == null) {
            throw new RuntimeException("登录失败"); // 密码错误
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return tokenService.createTokenAndRecordLoginUser(loginUser);
    }

    public String loginByPhone(String phone, String smsCode) {
        if (phone == null || phone.isBlank()) {
            throw new RuntimeException("手机号不能为空");
        }
        if (smsCode == null || smsCode.isBlank()) {
            throw new RuntimeException("验证码不能为空");
        }
        User user = loginUserMapper.selectUserByPhone(phone);
        if (user == null) {
            log.warn("用户账号不存在 {}", phone);
            throw new RuntimeException("用户账号不存在");
        }
        boolean verified = smsCodeService.verifySmsCode(phone, smsCode, 2);
        if (!verified) {
            throw new RuntimeException("验证码错误");
        }
        // TODO set user Authority
        LoginUser loginUser = new LoginUser(user, null);
        return tokenService.createTokenAndRecordLoginUser(loginUser);
    }

    public void logout() {
        // 获取 SecurityContextHolder userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        long userId = loginUser.getUser().getId();
        tokenService.removeLoginUser(userId);
    }

    public void sendSmsCodeForLogin(String phone) {
        if (phone == null || phone.isBlank()) {
            throw new RuntimeException("手机号不能为空");
        }
        User user = loginUserMapper.selectUserByPhone(phone);
        if (user == null) {
            log.warn("用户账号不存在 {}", phone);
            throw new RuntimeException("用户账号不存在");
        }
        smsCodeService.sendCodeByLogin(phone);
    }
}
