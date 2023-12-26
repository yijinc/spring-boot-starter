package org.example.controller;

import jakarta.annotation.Resource;
import org.example.domain.ResponseResult;
import org.example.domain.entity.User;
import org.example.dto.LoginParam;
import org.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class LoginController {

    /**
     * 当采用@Autowired时，根据类型注入，注入了 RedisTemplate<String, String> Bean
     * 当使用@Resource时，根据名称注入，注入了 RedisTemplate<Object, Object> Bean
     * 需要指定KeySerializer redisTemplate.setKeySerializer(new StringRedisSerializer())
     * **/
    @Resource
    private RedisTemplate<String, User> redisTemplate;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    ResponseResult<?> login(@RequestBody LoginParam param) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(param.getPhone(), param.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            if (authentication == null) {
                throw new RuntimeException("登录失败");
            }
            User user = (User) authentication.getPrincipal();
            String token = JwtUtil.encode(String.valueOf(user.getId()));
            redisTemplate.opsForValue().set("login:" + user.getId(), user, JwtUtil.JWT_EXPIRATION, TimeUnit.MILLISECONDS);
            return ResponseResult.ok(token);
        } catch (Exception e) {
            return ResponseResult.fail(411, e.getMessage());
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    ResponseResult<?> logout() {
        // 获取 SecurityContextHolder userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        // 删除 redis 用户
        redisTemplate.delete("login:" + user.getId());
        return ResponseResult.ok("退出成功");
    }
}
