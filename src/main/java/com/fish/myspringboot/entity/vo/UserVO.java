package com.fish.myspringboot.entity.vo;

import com.fish.myspringboot.entity.Blog;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class UserVO {
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 日志列表
     */
    private List<Blog> blogs;
}