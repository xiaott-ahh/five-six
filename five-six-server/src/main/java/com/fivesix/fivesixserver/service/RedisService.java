package com.fivesix.fivesixserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 设置带过期时间的缓存
    public void set(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    // 设置缓存
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // 根据 key 获得缓存
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 根据 key 删除缓存
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    // 根据 keys 集合批量删除缓存
    public Long delete(Set<String> keys) {
        return redisTemplate.delete(keys);
    }

    // 根据正则表达式匹配 keys 获取缓存
    public Set<String> getKeysByPattern(String pattern) {
        return redisTemplate.keys(pattern);
    }
}

