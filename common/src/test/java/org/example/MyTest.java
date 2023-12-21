package org.example;

import org.example.util.JwtUtil;
import org.junit.jupiter.api.Test;


public class MyTest {
    @Test
    public void test() {
        String token = JwtUtil.encode("hello");
        System.out.println("token: " + token);
        System.out.println("token2: " + JwtUtil.encode("hello"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String origin = JwtUtil.decode(token);
        System.out.println("origin text: " + origin);

        System.out.println(JwtUtil.isValid(token));
    }
}