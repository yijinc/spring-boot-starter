package org.example.security;

import org.example.domain.entity.User;
import org.example.domain.model.LoginUser;
import org.example.exception.UserNotFoundException;
import org.example.mapper.LoginUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    LoginUserMapper loginUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws AuthenticationException {
        User user = loginUserMapper.selectUserByName(username);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("用户不存在");
        }
        return new LoginUser(user, null);
    }
}
