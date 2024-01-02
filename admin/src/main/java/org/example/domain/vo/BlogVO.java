package org.example.domain.vo;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class BlogVO {
    private Long id; // id 字段注释2
    private String title; // 标题
    private String description; // 描述
    private String content; // 内容
    private Timestamp createTime; // 创建时间
    private Timestamp updateTime; // 更新时间
    private Long authorId; // 作者Id
    private String authorName; // 作者姓名
    private String authorEmail; // 作者邮箱
}