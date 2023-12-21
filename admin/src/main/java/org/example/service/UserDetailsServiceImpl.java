package org.example.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.example.dao.UserDao;
import org.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        Optional<User> user = userDao.findUserByName(phone);
        return user.map(LoginUser::new).orElseThrow(() -> new UsernameNotFoundException("用户没有找到"));
    }

    // 必须实现 UserDetails
    /**
     * redis 使用 Jackson2JsonRedisSerializer
     * 在json序列化时，实体类中所有的有返回值的方法都会将返回的值序列化，默认直接报错
     * 使用 JsonIgnoreProperties 忽略属性
     * **/
    @JsonIgnoreProperties({"enabled","accountNonExpired", "accountNonLocked", "credentialsNonExpired", "authorities","username"})
    static final class LoginUser extends User implements UserDetails {
        private static final List<GrantedAuthority> ROLE_USER = Collections
                .unmodifiableList(AuthorityUtils.createAuthorityList("ROLE_USER"));

        // fix com.fasterxml.jackson.databind.exc.InvalidDefinitionException:
        // Cannot construct instance of `org.example.service.UserDetailsServiceImpl$LoginUser
        // json序列化需要无参构造
        public LoginUser(){
            super();
        }
        public LoginUser(User user) {
            super(user.getId(), user.getName(), user.getPassword(), user.getPhone(), user.isDeleted(), user.getCreateTime(), user.getUpdateTime());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return ROLE_USER;
        }

        @Override
        public String getPassword() {
            return super.getPassword();
        }

        @Override
        public String getUsername() {
            return super.getPhone();
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
}
