package org.example.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.domain.entity.SmsCode;
import org.example.mapper.SmsCodeMapper;
import org.example.util.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

@Service
public class SmsCodeService {
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
        smsCode.setVerifyCode(mockGenerate());
        smsCode.setPhone(phone);
        smsCode.setType(type);
        smsCode.setCreateTime(LocalDateTime.now());
        int result = smsCodeMapper.insert(smsCode);
        if (result < 1) {
            throw new RuntimeException("验证码插入表失败");
        }
    }

    void sendCodeByLogin(String phone) {
        generateSmsCode(phone, 2);
    }

    public boolean verifySmsCode(String phone, String verifyCode, int type) {
        SmsCode smsCode = smsCodeMapper.selectOne(new QueryWrapper<SmsCode>()
                .eq("phone", phone)
                .eq("verify_code", verifyCode)
                .eq("type", type)
                .ge("create_time", LocalDateTime.now().minusSeconds(-60))
        );
        return Objects.nonNull(smsCode);
    }
}
