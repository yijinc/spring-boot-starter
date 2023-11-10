package com.fish.myspringboot.entity.vo;

import com.fish.myspringboot.entity.Blog;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class UserVO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private Timestamp createTime;
    private Timestamp updateTime;
    private List<Blog> blogs;
}
