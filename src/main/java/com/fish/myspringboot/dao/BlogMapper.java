package com.fish.myspringboot.dao;

import com.fish.myspringboot.dto.BlogDTO;
import com.fish.myspringboot.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogMapper {
    List<BlogDTO> queryBlogs(@Param("title") String title, @Param("description") String description);

    int insertBlog(Blog blog);

    int deleteBlog(@Param("id") long id);

    int updateBlog(Blog blog);

}
