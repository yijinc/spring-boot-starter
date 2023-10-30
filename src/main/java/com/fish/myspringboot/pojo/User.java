package com.fish.myspringboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String name;
    private String phone;
    private String password;
    private String email;
    private Boolean deleted;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String createBy;
    private String updateBy;
}
