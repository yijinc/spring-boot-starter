package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.domain.entity.User;
import org.example.domain.vo.UserBlogsVO;
import org.example.domain.vo.UserVO;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    UserBlogsVO queryUsersBlogs(@Param("id") Long id);
}
