package com.fish.myspringboot.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fish.myspringboot.entity.Blog;
import com.fish.myspringboot.entity.dto.BlogDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
    /***
     * 仍可直接使用 mybatis 用法
     * **/
    Page<BlogDTO> customSelectList(Page<Blog> page, @Param(Constants.WRAPPER) QueryWrapper<Blog> queryWrapper);


}
