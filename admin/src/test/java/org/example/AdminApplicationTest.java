package org.example;

import org.example.domain.entity.User;
import org.example.domain.model.LoginUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class AdminApplicationTest {

    @Autowired
    RedisTemplate<String, LoginUser> redisTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void testRedisTemplate() {
        User user = new User();
        LoginUser loginUser = new LoginUser(user, null);
        redisTemplate.opsForValue().set("loginUser:123", loginUser);
    }

    @Test
    public void test() {
        System.out.println("password:" + passwordEncoder.encode("password"));
        System.out.println("123456:" + passwordEncoder.encode("123456"));
        System.out.println("123456:" + passwordEncoder.encode("123456"));
    }
}
