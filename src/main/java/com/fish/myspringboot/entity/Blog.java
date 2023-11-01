package com.fish.myspringboot.entity;
import lombok.Data;
import java.sql.Timestamp;

@Data
public class Blog {
    private long id;
    private String title;
    private String description;
    private String content;
    private long userId;
    private Timestamp createTime;
    private Timestamp updateTime;
}