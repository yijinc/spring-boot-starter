package com.fish.myspringboot.controller;

import com.fish.myspringboot.entity.dto.UserDTO;
import com.fish.myspringboot.entity.vo.UserVO;
import com.fish.myspringboot.mapper.UserMapper;
import com.fish.myspringboot.entity.User;
import com.fish.myspringboot.entity.mapstruct.UserMapping;
import com.fish.myspringboot.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取所有用户
     * 获取所有用户desc
     * @return List<UserDTO>
     */
    @RequestMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> list = UserMapping.INSTANCE.toDTO(userMapper.selectList(null));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 添加用户
     * 添加用户 desc
     * @return int
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Integer> createUser(@RequestBody User user) {
        user.setCreateBy("admin");
        user.setUpdateBy("admin");
        int result = userMapper.insert(user);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    /**
     * 获取用户信息
     * 根据用户id 获取用户信息
     * @return 返回 UserDTO
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") long id) {
        UserDTO userDTO = UserMapping.INSTANCE.toDTO(userMapper.selectById(id));
        return new ResponseEntity(userDTO, HttpStatus.OK);
    }

    /**
     * 更新用户信息
     * 根据用户id 更新用户的信息
     * @return Integer
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> update(@PathVariable("id") long id, @RequestBody User user) {
        user.setId(id);
        user.setUpdateBy("admin");
        int result = userMapper.updateById(user);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    /**
     * 删除用户
     * 根据用户id删除
     * @return 响应描述
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@PathVariable("id") long id) {
        int result = userMapper.deleteById(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    /**
     * 获取用户日志列表
     * 根据用户id 获取用户的信息和他的blogs
     * @return UserVO
     */
    @RequestMapping(value = "/user/{id}/blogs", method = RequestMethod.GET)
    public ResponseResult<UserVO> getUserBlogs(@PathVariable("id") Long id) {
        return ResponseResult.success(userMapper.queryUsersBlogs(id));
    }
}
