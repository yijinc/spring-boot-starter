package com.fish.myspringboot.controller;

import com.fish.myspringboot.dto.UserDTO;
import com.fish.myspringboot.dao.UserMapper;
import com.fish.myspringboot.entity.User;
import com.fish.myspringboot.mapstruct.UserMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    // @RequestMapping 注解用于定义请求 URI 以访问 REST 端点，默认请求方法是 GET
    @RequestMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> list = UserMapping.INSTANCE.toDTO(userMapper.getAllUsers());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // @RequestBody 请求正文
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Integer> createUser(@RequestBody UserDTO userDTO) {
        User user = UserMapping.INSTANCE.toEntity(userDTO);
        user.setCreateBy("admin");
        user.setUpdateBy("admin");
        int result = userMapper.insertUser(user);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    // @PathVariable 路径变量
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") long id) {
        UserDTO userDTO = UserMapping.INSTANCE.toDTO(userMapper.getById(id));
        return new ResponseEntity(userDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> update(@PathVariable("id") long id, @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        User user = UserMapping.INSTANCE.toEntity(userDTO);
        user.setUpdateBy("admin");
        int result = userMapper.updateUser(user);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@PathVariable("id") long id) {
        int result = userMapper.deleteUser(id);
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
