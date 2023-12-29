package org.example.security;

import org.example.domain.entity.User;
import org.example.domain.model.LoginUser;
import org.example.mapper.LoginUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    LoginUserMapper loginUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = loginUserMapper.selectUserByName(username);
        if (Objects.isNull(user)) {
             /*
             * Notice：
             * 在 AuthenticationException 子类中
             * UsernameNotFoundException 会被捕获转化为 BadCredentialsException
             * 从而 e.getMessage = "用户名或密码错误"，如果要正确定义message，抛出其他异常类
             */
            throw new UsernameNotFoundException("用户不存在");
        }
        return new LoginUser(user, null);
    }
}
