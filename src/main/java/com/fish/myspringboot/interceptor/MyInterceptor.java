package com.fish.myspringboot.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class MyInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        log.debug(" -------- MyInterceptor.preHandle --- ");
        log.debug("Request URL: [{}]", request.getRequestURL());
        log.debug("Start Time: [{}]", startTime);

        request.setAttribute("startTime", startTime);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);

        log.debug(" -------- MyInterceptor.postHandle --- ");
        log.debug("Request URL: [{}]", request.getRequestURL());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);

        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        log.debug(" -------- MyInterceptor.afterCompletion --- ");
        log.debug("Request URL: [{}]", request.getRequestURL());
        log.debug("End Time: {[]}", endTime);
        log.debug("Time Taken: [{}]", endTime - startTime);
    }
}