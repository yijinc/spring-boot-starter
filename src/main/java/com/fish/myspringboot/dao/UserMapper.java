package com.fish.myspringboot.dao;

import com.fish.myspringboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    User getById(@Param("id") long id);

    int updateUser(User user);

    int insertUser(User user);

    int deleteUser(@Param("id") long id);

    List<User> getAllUsers();
}
