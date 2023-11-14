package com.fish.myspringboot.entity.dto;
import lombok.Data;
import java.sql.Timestamp;

@Data
public class BlogDTO {

    private long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 作者Id
     */
    private long authorId;

    /**
     * 作者姓名
     */
    private String authorName;

    /**
     * 作者邮箱
     */
    private String authorEmail;
}