package com.fish.myspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fish.myspringboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
