package org.example.annotation;

import java.lang.annotation.*;

/**
 * 自定义防重提交
 */
@Documented
@Target(ElementType.METHOD) // 可以用在方法上
@Retention(RetentionPolicy.RUNTIME) // 保留到虚拟机运行时,可通过反射获取
public @interface RepeatSubmit {
    /**
     * 防重提交，支持两种，一个是参数，一个是令牌
     * 参数：（在一时间段内）每次请求参数是否完全一样，一样视为重复提交
     * 令牌：每次请求携带令牌，校验令牌，失败则视为重复提交
     */
    enum Type { PARAM, TOKEN }
    /**
     * 默认防重提交，是参数
     * @return type
     */
    Type type() default Type.PARAM;

    /**
     * 令牌过期时间 默认 5 分钟 for Type.TOKEN
     */
    long tokenExpiredTime() default 2 * 60 * 1000;

    /**
     * 重复请求间隔时间 for Type.PARAM
     */
    long intervalTime() default 2 * 1000;
}
