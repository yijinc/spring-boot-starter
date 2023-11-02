package com.fish.myspringboot.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDTO {
    private long id;
    private String name;
    private String phone;
    private String email;
    private Timestamp createTime;
    private Timestamp updateTime;
}