package com.example.caching.caching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@CacheConfig(cacheNames = "books")
public class SimpleBookRepository implements BookRepository {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    @Cacheable(key = "#isbn", unless = "#result == null")
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        Book book = (Book) redisTemplate.opsForValue().get(isbn);
        return book;
    }

    @Override
    @CachePut(key = "#book.getIsbn()")
    public Book saveOrUpdate(Book book) {
        redisTemplate.opsForValue().set(book.getIsbn(), book);
        return book;
    }

    @Override
    @CacheEvict(key = "#isbn")
    public void deleteByIsbn(String isbn) {
        redisTemplate.delete(isbn);
    }
    
    private void simulateSlowService() {
        try {
            long time = 2000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
