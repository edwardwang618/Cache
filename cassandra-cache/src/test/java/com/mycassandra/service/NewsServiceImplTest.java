package com.mycassandra.service;

import com.mycassandra.entity.News;
import com.mycassandra.entity.NewsKey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NewsServiceImplTest {

    @Autowired
    NewsService newsService;

    @Test
    void listAll() {
        newsService.listAll().forEach(System.out::println);
    }

    @Test
    void getByKey() {
        NewsKey key = new NewsKey("Business1", 1);
        System.out.println(newsService.getByKey(key));
        System.out.println(newsService.getByKey(key));

    }

    @Test
    void saveOrUpdate() {
        NewsKey key = new NewsKey("Business2", 1);
        News news = new News();
        news.setKey(key);
        news.setContent("Business2 starts");
        news.setUpdateTime(LocalDateTime.now());
        System.out.println(newsService.saveOrUpdate(news));
    }

    @Test
    void deleteByKey() {
        NewsKey key = new NewsKey("Business1", 1);
        newsService.deleteByKey(key);
    }
}