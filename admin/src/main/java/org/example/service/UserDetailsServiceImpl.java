package org.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.domain.LoginUser;
import org.example.domain.User;
import org.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        User user = userMapper.selectOne(queryWrapper);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户没有找到");
        }
        return new LoginUser(user, null);
    }
}
