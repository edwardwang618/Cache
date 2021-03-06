package com.imcode.cms.service.impl;

import com.imcode.cms.entity.News;
import com.imcode.cms.service.INewsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsServiceImplTest {
    
    @Autowired
    private INewsService newsService;
    
    @Test
    public void testTransaction() {
        News news = newsService.getById(20);
        // news.setNewsId(null);
        // newsService.save(news);
        System.out.println(news);
    }
    
    @Test
    public void saveTest() {
        News news = new News();
        news.setTitle("News for test");
        
        newsService.save(news);
        System.out.println(news.getNewsId());
    }
    
    @Test
    public void getTest() {
        System.out.println(newsService.getById(622));
        // System.out.println(newsService.getById(100));
    }
    
    @Test
    public void updateTest() {
        News news = newsService.getById(622);

        System.out.println("---------------------");
        news.setTitle("Test News ~~~");
        newsService.updateById(news);
    
        System.out.println(newsService.getById(622));
    
    }
    
    @Test
    public void deleteTest() {
        newsService.removeById(20);
    }
    
   
}