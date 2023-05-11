package com.fish.myspringboot.controller;

import com.fish.myspringboot.mapper.UserMapper;
import com.fish.myspringboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/{userId}")
    User getUserById(@PathVariable int userId) {
        return userMapper.findById(userId);
    }
}
