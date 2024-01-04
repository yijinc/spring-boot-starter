package org.example.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.example.annotation.RepeatSubmit;
import org.example.exception.RepeatSubmitException;
import org.example.util.IpUtils;
import org.example.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
@Slf4j
public class RepeatSubmitAspect {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    TokenService tokenService;

    /**
     * 定义 @Pointcut注解表达式, 通过特定的规则来筛选连接点, 就是Pointcut，选中那几个你想要的方法
     * 在程序中主要体现为书写切入点表达式（通过通配、正则表达式）过滤出特定的一组 JointPoint连接点
     * 方式一：@annotation：当执行的方法上拥有指定的注解时生效（采用这）
     * 方式二：execution：一般用于指定方法的执行
     */
    @Pointcut("@annotation(repeatSubmit)")
    public void pointCutNoRepeatSubmit(RepeatSubmit repeatSubmit) {

    }

    @Before(value = "pointCutNoRepeatSubmit(repeatSubmit)", argNames = "joinPoint,repeatSubmit")
    public void before(JoinPoint joinPoint, RepeatSubmit repeatSubmit) throws JsonProcessingException {
        boolean isRepeated = false; // 是重复请求？
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        if (RepeatSubmit.Type.PARAM.equals(repeatSubmit.type())) {
            /*
            * 请求参数校验重复 key = repeat_submit:ip:token:method/uri:param:body
            */
            ObjectMapper mapper = new ObjectMapper();
            String ipAddr = IpUtils.getIpAddr(request);
            String token = tokenService.getToken(request);
            String api = request.getMethod() + request.getRequestURI();
            String param = mapper.writeValueAsString(request.getParameterMap());
            String body = mapper.writeValueAsString(joinPoint.getArgs());
            String key = "repeat_submit:" + ipAddr + ":" + token + ":" + api + ":" + param + ":" +body;
            log.info("使用请求参数校验重复请求：key = {}", key);
            isRepeated = Boolean.FALSE.equals(redisTemplate.opsForValue().setIfAbsent(key, "1", repeatSubmit.intervalTime(), TimeUnit.MILLISECONDS));
        } else {
            /*
             * TODO 请求token 校验重复
             */
        }
        if (isRepeated) {
            throw new RepeatSubmitException("请求过于频繁，请稍候再试");
        }
    }

}

