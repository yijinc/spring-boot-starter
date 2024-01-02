package org.example.controller;

import org.example.domain.ResponseResult;
import org.example.domain.entity.User;
import org.example.domain.model.LoginUser;
import org.example.domain.vo.UserBlogsVO;
import org.example.domain.vo.UserVO;
import org.example.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 获取所有用户
     * 获取所有用户desc
     * @return List<UserDTO>
     */
    @RequestMapping("/users")
    public ResponseResult<List<UserVO>> getAllUsers() {
        List<User> userList = userMapper.selectList(null);
        List<UserVO> list = userList.stream().map(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO, UserVO.class);
            return userVO;
        }).toList();
        return ResponseResult.ok(list);
    }

    /**
     * 添加用户
     * 添加用户 desc
     * @return int
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseResult<?> createUser(@RequestBody @Validated User user) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Timestamp now = new Timestamp(new Date().getTime());
        String password = user.getPassword();
        password = (password == null || password.isBlank()) ? "123456" : password;
        user.setPassword(passwordEncoder.encode(password));
        user.setCreateBy(loginUser.getUsername());
        user.setUpdateBy(loginUser.getUsername());
        user.setCreateTime(now);
        user.setUpdateTime(now);
        int result = userMapper.insert(user);
        return result > 0 ? ResponseResult.ok() : ResponseResult.fail();
    }

    /**
     * 获取用户信息
     * 根据用户id 获取用户信息
     * @return 返回 UserDTO
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseResult<UserVO> getUser(@PathVariable("id") long id) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userMapper.selectById(id), userVO);
        return ResponseResult.ok(userVO);
    }

    /**
     * 更新用户信息
     * 根据用户id 更新用户的信息
     * @return Integer
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseResult<?> update(@PathVariable("id") long id, @RequestBody User user) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setId(id);
        user.setUpdateBy(loginUser.getUsername());
        user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int result = userMapper.updateById(user);
        return result > 0 ? ResponseResult.ok() : ResponseResult.fail();
    }

    /**
     * 删除用户
     * 根据用户id删除
     * @return 响应描述
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseResult<?> delete(@PathVariable("id") long id) {
        int result = userMapper.deleteById(id);
        return result > 0 ? ResponseResult.ok() : ResponseResult.fail();
    }

    /**
     * 获取用户日志列表
     * 根据用户id 获取用户的信息和他的blogs
     * @return UserVO
     */
    @RequestMapping(value = "/user/{id}/blogs", method = RequestMethod.GET)
    public ResponseResult<UserBlogsVO> getUserBlogs(@PathVariable("id") Long id) {
        return ResponseResult.ok(userMapper.queryUsersBlogs(id));
    }
}
