package com.mycassandra.service.impl;

import com.mycassandra.dao.NewsRepository;
import com.mycassandra.entity.News;
import com.mycassandra.entity.NewsKey;
import com.mycassandra.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = {"cassandra.news"})
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<News> listAll() {
        List<News> newsList = new ArrayList<>();
        newsRepository.findAll().forEach(newsList::add);
        return newsList;
    }

    @Override
    @Cacheable(key = "#key")
    public News getByKey(NewsKey key) {
        simulateSlowService();
        return newsRepository.findById(key).orElse(null);
    }

    @Override
    @Transactional
    @CachePut(key = "#news.key")
    public News saveOrUpdate(News news) {
        newsRepository.save(news);
        return news;
    }

    @Override
    @CacheEvict(key = "#key")
    public void deleteByKey(NewsKey key) {
        newsRepository.deleteById(key);
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
