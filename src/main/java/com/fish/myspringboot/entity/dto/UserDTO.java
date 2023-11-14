package com.fish.myspringboot.entity.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDTO {
    private long id;

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
}