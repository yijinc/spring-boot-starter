package org.example.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_sms_verify")
public class SmsCode implements Serializable {
    /** id */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 手机号 */
    private String phone;

    /** 短信类型：1、注册，2、登录；3、找回密码 */
    private int type;

    /** 验证码回执编号 */
    private String receiptCode;

    /** 短信验证码 */
    private String verifyCode;

    /** 鉴权状态：1、未使用，2、已使用；3、已过期 */
    private int authStatus;

    /** 验证码创建时间 */
    private LocalDateTime createTime;

    /** 平台 */
    private String platform;
}
