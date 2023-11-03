package com.fish.myspringboot.controller;

import com.fish.myspringboot.dao.BlogMapper;
import com.fish.myspringboot.dto.BlogDTO;
import com.fish.myspringboot.entity.Blog;
import com.fish.myspringboot.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    BlogMapper blogMapper;

    @GetMapping("/blogs")
    public ResponseResult<List<BlogDTO>> getBlogs(@RequestParam(name = "title", required = false) String title,
                                   @RequestParam(name = "description", required = false) String description
    ) {
        return ResponseResult.success(blogMapper.queryBlogs(title, description));
    }

    @PostMapping("/blog")
    public ResponseResult create(@RequestBody Blog blog) {
        blog.setUserId(1111122);
        int result = blogMapper.insertBlog(blog);
        if (result > 0) {
            return ResponseResult.success("新增成功");
        } else {
            return ResponseResult.error("新增失败");
        }
    }

    @DeleteMapping("/blog/{id}")
    public Object delete(@PathVariable("id") long id) {
        int result = blogMapper.deleteBlog(id);
        if (result > 0) {
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.error("删除失败");
        }
    }

    @PutMapping("/blog/{id}")
    public Object update(@PathVariable("id") long id, Blog blog) {
        blog.setId(id);
        int result = blogMapper.updateBlog(blog);
        if (result > 0) {
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.error("删除失败");
        }
    }
}