package com.fish.myspringboot.mapper;

import com.fish.myspringboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    User getById(@Param("id") int id);

    int updateUser(User user);

    int insertUser(User user);

    int deleteUser(@Param("id") int id);

    List<User> getAllUsers();
}
