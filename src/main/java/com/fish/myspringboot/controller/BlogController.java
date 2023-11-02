package com.fish.myspringboot.controller;

import com.fish.myspringboot.dao.BlogMapper;
import com.fish.myspringboot.dto.BlogDTO;
import com.fish.myspringboot.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    BlogMapper blogMapper;

    @GetMapping("/blogs")
    public List<BlogDTO> getBlogs(@RequestParam(name = "title", required = false) String title,
                                  @RequestParam(name = "description", required = false) String description
    ) {
        return blogMapper.queryBlogs(title, description);
    }

    @PostMapping("/blog")
    public Object create(@RequestBody Blog blog) {
        blog.setUserId(1111122);
        return blogMapper.insertBlog(blog);
    }

    @DeleteMapping("/blog/{id}")
    public Object delete(@PathVariable("id") long id) {
        return blogMapper.deleteBlog(id);
    }

    @PutMapping("/blog/{id}")
    public Object update(@PathVariable("id") long id, Blog blog) {
        blog.setId(id);
        return  blogMapper.updateBlog(blog);
    }
}