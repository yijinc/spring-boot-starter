package com.fish.myspringboot.mapper;

import com.fish.myspringboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User getById(@Param("id") int id);

    int updateUser(User user);

    int insertUser(User user);
}
