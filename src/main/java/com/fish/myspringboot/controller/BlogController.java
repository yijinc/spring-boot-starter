package com.fish.myspringboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fish.myspringboot.mapper.BlogMapper;
import com.fish.myspringboot.dto.BlogDTO;
import com.fish.myspringboot.entity.Blog;
import com.fish.myspringboot.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BlogController {

    @Autowired
    BlogMapper blogMapper;

    @GetMapping("/blogs")
    public ResponseResult<List<Blog>> getBlogs(@RequestParam(name = "title", required = false) String title,
                                   @RequestParam(name = "description", required = false) String description
    ) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper();
        queryWrapper.like(!StringUtils.isEmpty(title),"title", title)
                .like(!StringUtils.isEmpty(description), "description", description);
        return ResponseResult.success(blogMapper.selectList(queryWrapper));
    }

    @PostMapping("/blog")
    public ResponseResult create(@RequestBody Blog blog) {
        blog.setUserId(1111122);
        int result = blogMapper.insert(blog);
        if (result > 0) {
            return ResponseResult.success("新增成功");
        } else {
            return ResponseResult.error("新增失败");
        }
    }

    @DeleteMapping("/blog/{id}")
    public Object delete(@PathVariable("id") long id) {
        int result = blogMapper.deleteById(id);
        if (result > 0) {
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.error("删除失败");
        }
    }

    @PutMapping("/blog/{id}")
    public Object update(@PathVariable("id") long id, Blog blog) {
        blog.setId(id);
        int result = blogMapper.updateById(blog);
        if (result > 0) {
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.error("删除失败");
        }
    }
}