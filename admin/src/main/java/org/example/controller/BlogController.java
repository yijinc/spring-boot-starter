package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.domain.ResponseResult;
import org.example.domain.entity.Blog;
import org.example.domain.param.BlogBody;
import org.example.domain.param.BlogQueryParam;
import org.example.domain.vo.BlogVO;
import org.example.mapper.BlogMapper;
import org. springframework. beans. BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org. springframework. validation. annotation. Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class BlogController {

    @Autowired
    BlogMapper blogMapper;

    /**
     * 查询博客
     * 分页查询博客列表
     */
    @GetMapping("/blogs")
    public ResponseResult<Page<BlogVO>> getBlogs(BlogQueryParam params) {
        Page<BlogVO> page = new Page();
        page.setCurrent(params.getCurrent());
        page.setSize(params.getPageSize());
        page.addOrder(new OrderItem("create_time", false));
        QueryWrapper<BlogQueryParam> queryWrapper = new QueryWrapper();
        queryWrapper.like(StringUtils.isNotEmpty(params.getTitle()),"title", params.getTitle())
                .like(StringUtils.isNotEmpty(params.getDescription()), "description", params.getDescription());

        return ResponseResult.ok(blogMapper.customSelectList(page, queryWrapper));
    }

    /**
     * 新增博客
     * 新增
     */
    @PostMapping("/blog")
    public ResponseResult create(@RequestBody @Validated BlogBody body) {
        Blog blog = new Blog();
        BeanUtils.copyProperties(body, blog);
        blog.setUserId(1111122);
        int result = blogMapper.insert(blog);
        if (result > 0) {
            return ResponseResult.ok("新增成功");
        } else {
            return ResponseResult.fail("新增失败");
        }
    }

    /**
     * 删除博客
     * 删除
     */
    @DeleteMapping("/blog/{id}")
    public Object delete(@PathVariable("id") long id) {
        int result = blogMapper.deleteById(id);
        if (result > 0) {
            return ResponseResult.ok("删除成功");
        } else {
            return ResponseResult.fail("删除失败");
        }
    }

    /**
     * 更新博客
     * 更新
     */
    @PutMapping("/blog/{id}")
    public Object update(@PathVariable("id") long id, @RequestBody @Validated BlogBody body) {
        Blog blog = new Blog();
        BeanUtils.copyProperties(body, blog);
        blog.setUserId(1111122);
        blog.setId(id);
        int result = blogMapper.updateById(blog);
        if (result > 0) {
            return ResponseResult.ok("删除成功");
        } else {
            return ResponseResult.fail("删除失败");
        }
    }
}