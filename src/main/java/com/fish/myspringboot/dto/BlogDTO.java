package com.fish.myspringboot.dto;
import lombok.Data;
import java.sql.Timestamp;

@Data
public class BlogDTO {
    private long id;
    private String title;
    private String description;
    private String content;
    private Timestamp createTime;
    private Timestamp updateTime;
    private long authorId;
    private String authorName;
    private String authorEmail;
}