package org.example;

import org.example.utils.JwtUtil;

import java.util.UUID;


public class MyTest {

    public static void test() {
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

    public static void main(String[] args) {
//        test();

//        StringBuilder stringBuilder = new StringBuilder("0123345678");
//        System.out.println(stringBuilder.substring(2,6));

//        System.out.println(new Date().getTime());
//        System.out.println(System.currentTimeMillis());

        UUID uuid = UUID.randomUUID();
        UUID uuid1 = new UUID(12L,12L);
        System.out.println(uuid);
        System.out.println(uuid1);

    }

}
