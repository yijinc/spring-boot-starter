package com.fish.myspringboot.controller;

import com.fish.myspringboot.mapper.UserMapper;
import com.fish.myspringboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

// import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    // @RequestMapping 注解用于定义请求 URI 以访问 REST 端点，默认请求方法是 GET
    @RequestMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userMapper.getAllUsers(), HttpStatus.OK);
    }

    // @RequestBody 请求正文
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        user.setCreateBy("admin");
        user.setUpdateBy("admin");
        int result = userMapper.insertUser(user);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    // @PathVariable 路径变量
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") BigInteger id) {
        User user = userMapper.getById(id);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> update(@PathVariable("id") BigInteger id, @RequestBody User user) {
        user.setId(id);
        user.setUpdateBy("admin");
        int result = userMapper.updateUser(user);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        int result = userMapper.deleteUser(Integer.parseInt(id));
        return new ResponseEntity(result, HttpStatus.OK);
    }
//
//    // @RequestParam 从请求 URL 中读取请求参数
//    @RequestMapping(value = "/user/search")
//    public ResponseEntity<Object> searchUsers(
//            @RequestParam(value = "name", required = false) String name,
//            @RequestParam(value = "bio", required = false) String bio) {
//    }
}
