package org.example.web.service;

import org.example.domain.model.LoginUser;
import org.example.util.IpUtils;
import org.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class LoginService {

    /**
     * 当采用@Autowired时，根据类型注入，注入了 RedisTemplate<String, String> Bean
     * 当使用@Resource时，根据名称注入，注入了 RedisTemplate<Object, Object> Bean
     * 需要指定KeySerializer redisTemplate.setKeySerializer(new StringRedisSerializer())
     * **/
    @Autowired
    private RedisTemplate<String, LoginUser> redisTemplate;

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
        Long userId = loginUser.getUser().getId();
        String token = JwtUtil.encode(String.valueOf(userId));
        // 记录登录信息
        loginUser.setToken(token);
        loginUser.setLoginIp(IpUtils.getIpAddr());
        long now = new Date().getTime();
        loginUser.setLoginTime(now);
        loginUser.setExpireTime(new Date(now + JwtUtil.JWT_EXPIRATION).getTime());
        redisTemplate.opsForValue().set("loginUser:" + userId, loginUser, JwtUtil.JWT_EXPIRATION, TimeUnit.MILLISECONDS);
        return token;
    }

    public void logout() {
        // 获取 SecurityContextHolder userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        long userId = loginUser.getUser().getId();
        // 删除 redis 用户
        redisTemplate.delete("loginUser:" + userId);
    }
}
