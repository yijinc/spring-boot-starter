package org.example.web.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.domain.model.LoginUser;
import org.example.util.IpUtils;
import org.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class TokenService {

    @Value("${token.prefix}")
    private String TOKEN_PREFIX;

    @Value("${token.header}")
    private String TOKEN_HEADER; // token || Authorization

    @Value("${token.secret}")
    private String TOKEN_SECRET;

    @Value("${token.expiration}")
    private long TOKEN_EXPIRATION;

    @Autowired
    private RedisTemplate<String, LoginUser> redisTemplate;

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);
        if (token != null && !token.isBlank() && token.startsWith(TOKEN_PREFIX)) {
            token = token.replace(TOKEN_PREFIX, "");
        }
        return token;
    }

    /**
     * 生成 token
     *
     * @param id
     * @return token
     */
    private String createToken(String id) {
        return TOKEN_PREFIX + JwtUtil.encode(id, TOKEN_SECRET, TOKEN_EXPIRATION);
    }

    /**
     * 获取登录用户信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        String token = getToken(request);
        String userId = JwtUtil.decode(token);
        if (StringUtils.hasText(userId)) {
            // 从 redis 中获取用户信息
            return redisTemplate.opsForValue().get("loginUser:" + userId);
        }
        return null;
    }

    /**
     * 设置登录用户信息 并返回 token
     */
    public String createTokenAndRecordLoginUser(LoginUser loginUser) {
        Long userId = loginUser.getUser().getId();
        String token = createToken(String.valueOf(userId));
        long now = new Date().getTime();
        // 记录用户登录信息
        loginUser.setToken(token);
        loginUser.setLoginIp(IpUtils.getIpAddr());
        loginUser.setLoginTime(now);
        loginUser.setExpireTime(new Date(now + TOKEN_EXPIRATION).getTime());
        // 保存到 redis
        redisTemplate.opsForValue().set("loginUser:" + userId, loginUser, TOKEN_EXPIRATION, TimeUnit.MILLISECONDS);
        return token;
    }

    /**
     * 删除用户身份信息
     */
    public void removeLoginUser(Long userId) {
        // 删除 redis 用户
        redisTemplate.delete("loginUser:" + userId);
    }
    public void removeLoginUser(HttpServletRequest request) {
        String token = getToken(request);
        String userId = JwtUtil.decode(token);
        redisTemplate.delete("loginUser:" + userId);
    }
}
