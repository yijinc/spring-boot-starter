package org.example.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.entity.SmsCode;
import org.example.mapper.SmsCodeMapper;
import org.example.util.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

@Slf4j
@Service
public class SmsCodeService {

    static int EXPIRATION = 60 * 2;

    @Autowired
    SmsCodeMapper smsCodeMapper;

    private String mockGenerate() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }

    void generateSmsCode(String phone, int type) {
        if (!RegexUtils.isPhone(phone)) {
            throw new RuntimeException("手机号错误");
        }
        // TODO 调第三方发送验证码
        SmsCode smsCode = new SmsCode();
        String code = mockGenerate();
        smsCode.setVerifyCode(code);
        smsCode.setPhone(phone);
        smsCode.setType(type);
        smsCode.setAuthStatus(1);
        smsCode.setCreateTime(new Timestamp(System.currentTimeMillis()));
        log.info("手机号登录：{}，发送验证码为：{}", phone, code);
        int result = smsCodeMapper.insert(smsCode);
        if (result < 1) {
            log.error("手机号登录：{}，验证码插入失败: {}", phone, code);
            throw new RuntimeException("验证码插入表失败");
        }
    }

    void sendCodeByLogin(String phone) {
        int codeType = 2; // 登录类型验证码
        // 查询是否已发送验证码，避免重复发送
        long hasCount = smsCodeMapper.selectCount(new QueryWrapper<SmsCode>()
                .eq("phone", phone)
                .eq("type", codeType)
                .eq("auth_status", 1)
                .ge("create_time", LocalDateTime.now().minusSeconds(EXPIRATION))
        );
        if (hasCount > 0) {
            log.error("已发送验证码，请勿频繁发送: {}", phone);
            throw new RuntimeException("已发送验证码，请勿频繁发送");
        }
        generateSmsCode(phone, codeType);
    }

    public boolean verifySmsCode(String phone, String verifyCode, int type) {
        SmsCode smsCode = smsCodeMapper.selectOne(new QueryWrapper<SmsCode>()
                .eq("phone", phone)
                .eq("verify_code", verifyCode)
                .eq("type", type)
                .eq("auth_status", 1)
                .ge("create_time", LocalDateTime.now().minusSeconds(EXPIRATION))
        );
        if (Objects.nonNull(smsCode)) {
            smsCode.setAuthStatus(2); // 标记为使用
            smsCodeMapper.updateById(smsCode);
            return true;
        }
        return false;
    }
}
