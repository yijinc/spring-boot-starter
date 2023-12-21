package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class AppTest {

    @Test
    public void test() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("password:" + encoder.encode("password"));
        System.out.println("123456:" + encoder.encode("123456"));
        System.out.println("123456:" + encoder.encode("123456"));
    }
}
