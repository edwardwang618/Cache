package com.mycassandra.dao;

import com.mycassandra.entity.News;
import com.mycassandra.entity.NewsKey;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<News, NewsKey> {
}
