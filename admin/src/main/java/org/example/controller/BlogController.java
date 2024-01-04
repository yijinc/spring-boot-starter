package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.example.annotation.RepeatSubmit;
import org.example.domain.ResponseResult;
import org.example.domain.entity.Blog;
import org.example.domain.model.LoginUser;
import org.example.domain.param.BlogBody;
import org.example.domain.param.BlogQueryParam;
import org.example.domain.vo.BlogVO;
import org.example.mapper.BlogMapper;
import org.example.service.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
public class BlogController {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    BlogService blogService;

    /**
     * 接口名称：查询博客
     * 接口说明：分页查询博客列表
     * @return ResponseResult<Page<BlogVO>>
     */
    @GetMapping("/blogs")
    @RepeatSubmit
    public ResponseResult<Page<BlogVO>> getBlogs(BlogQueryParam params) {
        Page<BlogVO> page = new Page<>();
        page.setCurrent(params.getCurrent());
        page.setSize(params.getPageSize());
        page.addOrder(new OrderItem("create_time", false));
        QueryWrapper<BlogQueryParam> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(params.getTitle()),"title", params.getTitle())
                .like(StringUtils.isNotEmpty(params.getDescription()), "description", params.getDescription());

        return ResponseResult.ok(blogMapper.customSelectList(page, queryWrapper));
    }

    /**
     * 新增博客
     * 新增
     */
    @PostMapping("/blog")
    @RepeatSubmit
    public ResponseResult<?> create(@RequestBody @Validated BlogBody body) {
        Blog blog = new Blog();
        BeanUtils.copyProperties(body, blog);
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = loginUser.getUser().getId();
        blog.setUserId(loginUser.getUser().getId());
        int result = blogMapper.insert(blog);
        if (result > 0) {
            return ResponseResult.ok();
        } else {
            log.error("新增blog 失败：{} by userId={}", blog, userId);
            return ResponseResult.fail();
        }
    }

    /**
     * 删除博客
     * 删除
     */
    @DeleteMapping("/blog/{id}")
    public Object delete(@PathVariable("id") long id) {
        boolean ok = blogService.deleteBlogById(id);
        return ok ? ResponseResult.ok() : ResponseResult.fail("删除失败");
    }

    /**
     * 更新博客
     * 更新
     */
    @PutMapping("/blog/{id}")
    public Object update(@PathVariable("id") long id, @RequestBody @Validated BlogBody body) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = loginUser.getUser().getId();
        Blog blog = new Blog();
        BeanUtils.copyProperties(body, blog);
        blog.setUserId(userId);
        blog.setId(id);
        int result = blogMapper.updateById(blog);
        if (result > 0) {
            return ResponseResult.ok();
        } else {
            log.error("更新 blog 失败：blogId={} by userId={}", id, userId);
            return ResponseResult.fail("删除失败");
        }
    }
}