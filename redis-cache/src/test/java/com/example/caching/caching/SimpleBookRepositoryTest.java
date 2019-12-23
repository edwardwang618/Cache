package com.example.caching.caching;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class SimpleBookRepositoryTest {

    @Autowired
    private BookRepository simpleBookRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void getByIsbnTest() {
        System.out.println(simpleBookRepository.getByIsbn("isbn1"));
        System.out.println(simpleBookRepository.getByIsbn("isbn1"));
    }

    @Test
    void saveOrUpdate() {
        Book book = new Book();
        book.setIsbn("isbn1");
        book.setTitle("Title: isbn1");
        simpleBookRepository.saveOrUpdate(book);
    }

    @Test
    void deleteByIsbn() {
    }
}