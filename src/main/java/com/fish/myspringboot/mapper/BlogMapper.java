package com.fish.myspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fish.myspringboot.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
}
