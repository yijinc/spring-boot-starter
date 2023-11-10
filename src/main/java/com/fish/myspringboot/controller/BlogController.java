package com.fish.myspringboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fish.myspringboot.entity.dto.BlogDTO;
import com.fish.myspringboot.entity.requestParam.BlogQueryParam;
import com.fish.myspringboot.mapper.BlogMapper;
import com.fish.myspringboot.entity.Blog;
import com.fish.myspringboot.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class BlogController {

    @Autowired
    BlogMapper blogMapper;

    @GetMapping("/blogs")
    public ResponseResult<Page<BlogDTO>> getBlogs(BlogQueryParam params) {
        Page<BlogDTO> page = new Page();
        page.setCurrent(params.getCurrent());
        page.setSize(params.getPageSize());
        page.addOrder(new OrderItem("id", true));
        QueryWrapper<BlogQueryParam> queryWrapper = new QueryWrapper();
        queryWrapper.like(StringUtils.isNotEmpty(params.getTitle()),"title", params.getTitle())
                .like(StringUtils.isNotEmpty(params.getDescription()), "description", params.getDescription());

        return ResponseResult.success(blogMapper.customSelectList(page, queryWrapper));
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