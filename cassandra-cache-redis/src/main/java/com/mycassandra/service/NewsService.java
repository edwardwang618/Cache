package com.mycassandra.service;

import com.mycassandra.entity.News;
import com.mycassandra.entity.NewsKey;

import java.util.List;

public interface NewsService {
    List<News> listAll();

    News getByKey(NewsKey key);

    News saveOrUpdate(News news);

    void deleteByKey(NewsKey key);
}
