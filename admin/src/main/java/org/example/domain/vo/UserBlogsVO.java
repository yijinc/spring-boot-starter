package org.example.domain.vo;

import lombok.Data;
import org.example.domain.entity.Blog;
import java.sql.Timestamp;
import java.util.List;

@Data
public class UserBlogsVO {
    private Long id;

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

    /**
     * 日志列表
     */
    private List<Blog> blogs;
}
