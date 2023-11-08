package com.fish.myspringboot.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.sql.Timestamp;

@Data
@TableName("t_user")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
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
