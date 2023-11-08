package com.fish.myspringboot.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.sql.Timestamp;

@Data
@TableName("t_blog")
public class Blog {
    @TableId(value = "id", type = IdType.AUTO)
    private long id;
    private String title;
    private String description;
    private String content;
    private long userId;
    private Timestamp createTime;
    private Timestamp updateTime;
}