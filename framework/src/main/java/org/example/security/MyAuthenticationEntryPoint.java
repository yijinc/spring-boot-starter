package org.example.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.domain.ResponseResult;
import org.example.enums.StatusCode;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        if (authException instanceof BadCredentialsException) {
            response.getWriter().write(objectMapper.writeValueAsString(ResponseResult.fail("用户密码错误")));
            return;
        }
        response.getWriter().write(objectMapper.writeValueAsString(ResponseResult.fail(StatusCode.UNAUTHORIZED)));
    }
}
