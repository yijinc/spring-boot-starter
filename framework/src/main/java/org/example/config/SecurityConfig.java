package org.example.config;

import jakarta.servlet.DispatcherType;
import org.example.security.MyAccessDeniedHandler;
import org.example.security.MyAuthenticationEntryPoint;
import org.example.security.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasAuthority;
import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;
import static org.springframework.security.authorization.AuthorizationManagers.allOf;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity // @EnableWebSecurity：自动为每个请求注册 SecurityFilterChain
@EnableMethodSecurity // @EnableMethodSecurity： 可以用 @PreAuthorize、@PostAuthorize、@PreFilter 和 @PostFilter 注解任何 Spring 管理的类或方法，以授权方法调用，包括参数和返回值。
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    // 配置 SecurityFilterChain bean, 用于配置安全过滤器链
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        RequestMatcher printview = (request) -> request.getParameter("print") != null;
        // @formatter:off
        http
                // 禁用 csrf（所有非get请求会默认携带参数_csrf 校验）
                .csrf(AbstractHttpConfigurer::disable)
                // 不使用 session/cookie
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 禁用/login, /logout 页面，2个路径作为 api接口使用
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                // 路由配置
                .authorizeHttpRequests((authorize) -> authorize
                        // 接口放行
                        .requestMatchers("/login", "/code/login", "/send/smsCode").permitAll()
                        //  要求 /user/** 只能被具有 USER 权限的用户访问
                        .requestMatchers("/user/**").hasAuthority("USER")
                        // 按 http 方法授权
                        .requestMatchers(HttpMethod.DELETE).hasAuthority("write")
                        // 发生页面重定向和 error 也会进行授权，可以直接放行，以允许 Spring MVC 渲染视图
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                        // 使用自定义的 Matcher
                        .requestMatchers(printview).hasAuthority("TEST")
                        // 使用 access
                        .requestMatchers("/db/**").access(allOf(hasAuthority("db"), hasRole("ADMIN")))
                        .anyRequest().authenticated()
                )
                // 配置自定义 认证 / 授权 异常处理
                .exceptionHandling(exceptionHandlingConfigurer -> {
                    exceptionHandlingConfigurer
                            .authenticationEntryPoint(new MyAuthenticationEntryPoint())
                            .accessDeniedHandler(new MyAccessDeniedHandler());
                })
                // 将 jwtAuthenticationTokenFilter 过滤器添加到过滤器链
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // @formatter:on
        return http.build();
    }

    // 配置密码编码器 bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
