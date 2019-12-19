package com.imcode.cms.service.impl;

import com.imcode.cms.entity.News;
import com.imcode.cms.mapper.NewsMapper;
import com.imcode.cms.service.INewsService;
import com.imcode.common.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@CacheConfig(cacheNames = "cms.news")
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {
    
    @Override
    @Cacheable(key = "#id")
    public News getById(Serializable id) {
    
        return super.getById(id);
    }
    
    @Override
    @Transactional
    @CachePut(key = "#news.newsId")
    public News save(News news) {
        
        super.save(news);
        return news;
    }
    
    @Override
    @CachePut(key = "#news.newsId")
    public News updateById(News news) {
        
        return super.updateById(news);
    }
    
    @Override
    @CacheEvict(key = "#id")
    public boolean removeById(Serializable id) {
        
        return super.removeById(id);
    }
}
