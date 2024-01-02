package org.example.domain.vo;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class UserVO {
    private long id;

    /**
     * 字段注释1
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
