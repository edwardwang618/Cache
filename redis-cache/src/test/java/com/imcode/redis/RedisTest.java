package com.imcode.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    
    @Test
    public void getTest() {
        System.out.println(redisTemplate.opsForValue().get("cms.news::20"));
        // System.out.println(redisTemplate.opsForValue().get("k2"));
    }
    
    @Test
    public void transactionTest() {
        redisTemplate.multi();
        redisTemplate.opsForValue().set("k2", "Hello Redis......");
        // int i = 100 / 0;
        redisTemplate.opsForValue().set("k3", "Hello Redis......");
        redisTemplate.exec();
    }
   
}