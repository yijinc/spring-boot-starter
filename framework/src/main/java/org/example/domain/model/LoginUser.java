package org.example.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.example.domain.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * redis 使用 Jackson2JsonRedisSerializer
 * 在json序列化时，实体类中所有的有返回值的方法都会将返回的值序列化，默认直接报错
 * 使用 JsonIgnoreProperties 忽略属性
 * **/
@Data
@JsonIgnoreProperties({"enabled", "accountNonExpired", "accountNonLocked", "credentialsNonExpired"})
public class LoginUser implements UserDetails {

    /**
     * 用户名，一般为手机号或名称、唯一标识
     */
    private User user;

    /**
     * 用户 TOKEN
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String loginIp;

    /**
     * 权限列表
     */
    private List<? extends GrantedAuthority> authorities;

    public LoginUser(User user, List<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> permissions) {
        if (permissions == null) {
            this.authorities = null;
        } else {
            this.authorities = permissions.stream().map(SimpleGrantedAuthority::new).toList();
        }
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
