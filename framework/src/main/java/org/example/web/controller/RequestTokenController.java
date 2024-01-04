package org.example.web.controller;

import org.example.domain.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class RequestTokenController {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @GetMapping("/request/token")
    ResponseResult<String> getFormToken() {
        String requestToken = UUID.randomUUID().toString();
        String key = "repeat_submit:" + requestToken;
        redisTemplate.opsForValue().set(key, "1", 5, TimeUnit.MINUTES);
        return ResponseResult.ok(requestToken);
    }
}
