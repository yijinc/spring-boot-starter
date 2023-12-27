package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class FrameworkTest {
    static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void test() {
        System.out.println("password:" + passwordEncoder.encode("password"));
        System.out.println("123456:" + passwordEncoder.encode("123456"));
        System.out.println("123456:" + passwordEncoder.encode("123456"));
    }
}
