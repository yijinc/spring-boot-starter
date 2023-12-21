package org.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * redis 使用 Jackson2JsonRedisSerializer
 * 在json序列化时，实体类中所有的有返回值的方法都会将返回的值序列化，默认直接报错
 * 使用 JsonIgnoreProperties 忽略属性
 * **/
@JsonIgnoreProperties({"enabled", "accountNonExpired", "accountNonLocked", "credentialsNonExpired"})
public class LoginUser implements UserDetails, Serializable {
    private User user;
    private Collection<? extends GrantedAuthority> authorities;

    public LoginUser(User user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getPhone();
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
        return !this.user.getDeleted();
    }
}
