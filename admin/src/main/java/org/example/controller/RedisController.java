package org.example.controller;

import org.example.domain.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseResult<?> save(@RequestParam("key") String key, @RequestParam("value") String value){
        redisTemplate.opsForValue().set(key, value);
        return ResponseResult.ok(null);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseResult<?> get(@RequestParam("key") String key){
        return ResponseResult.ok(redisTemplate.opsForValue().get(key));
    }
}
