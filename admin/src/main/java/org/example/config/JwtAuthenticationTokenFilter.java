package org.example.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.domain.User;
import org.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisTemplate<String, User> redisTemplate;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取 token
        String token = request.getHeader("token");
        if (JwtUtil.isValid(token)) {
            // 解析 token
            String userId = JwtUtil.decode(token);
            if (StringUtils.hasText(userId)) {
                // 从 redis 中获取用户信息
                User user = redisTemplate.opsForValue().get("login:" + userId);
                if (Objects.nonNull(user)) {
                    // TODO authorities 权限信息
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,null, null);
                    // 存入 SecurityContextHolder
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        // 放行，继续执行
        filterChain.doFilter(request, response);
    }
}
