package com.fish.myspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fish.myspringboot.entity.User;
import com.fish.myspringboot.entity.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    UserVO queryUsersBlogs(@Param("id") Long id);
}
