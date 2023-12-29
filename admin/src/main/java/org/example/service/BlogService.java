package org.example.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.entity.Blog;
import org.example.domain.model.LoginUser;
import org.example.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BlogService {

    @Autowired
    BlogMapper blogMapper;

    public boolean deleteBlogById(long id) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = loginUser.getUser().getId();
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("user_id", userId);
        int result = blogMapper.delete(queryWrapper);
        if (result <= 0) {
            log.error("删除 blog 失败：blogId={} by userId={}", id, userId);
        }
        return result > 0;
    }
}
