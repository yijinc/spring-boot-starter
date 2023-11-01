package com.fish.myspringboot.controller;

import com.fish.myspringboot.mapper.BlogMapper;
import com.fish.myspringboot.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    BlogMapper blogMapper;
    @GetMapping("/blogs")
    public List<Blog> getBlogs(@RequestParam("title") String title, @RequestParam("description") String description) {
        return blogMapper.queryBlogs(title, description);
    }
}