package org.example.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.domain.entity.Blog;
import org.example.domain.param.BlogQueryParam;
import org.example.domain.vo.BlogVO;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
    /***
     * 直接使用 mybatis 用法
     * **/
    Page<BlogVO> customSelectList(Page<BlogVO> page, @Param(Constants.WRAPPER) QueryWrapper<BlogQueryParam> queryWrapper);

}
