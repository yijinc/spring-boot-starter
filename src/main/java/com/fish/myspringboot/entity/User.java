package com.fish.myspringboot.entity;
import lombok.Data;
import java.sql.Timestamp;

@Data
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
