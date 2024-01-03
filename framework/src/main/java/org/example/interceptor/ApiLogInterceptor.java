package org.example.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class ApiLogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long requestStartTime = System.currentTimeMillis();
        log.info("---------- MyInterceptor.preHandle ----------");
        log.info("---------- Request URL: {} ----------", request.getRequestURL());
        log.info("---------- Start Time: {} ----------", requestStartTime);
        request.setAttribute("requestStartTime", requestStartTime);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        log.info("---------- MyInterceptor.postHandle ----------");
        log.info("---------- Request URL: {}", request.getRequestURL());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        long requestStartTime = (Long) request.getAttribute("requestStartTime");
        long endTime = System.currentTimeMillis();
        log.info("---------- MyInterceptor.afterCompletion ----------");
        log.info("---------- Request URL: {}", request.getRequestURL());
        log.info("---------- End Time: {} ----------", endTime);
        log.info("---------- Time Taken: {} ----------", endTime - requestStartTime);
    }
}
