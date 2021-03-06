##Spring Cache with Redis

This demo is to show the caching feature of spring cache module using redis as the cache database.

First we have a table named "cms_news" in MySQL whose structure is below:

| Field        | Type          | Null | Key | Default | Extra          |
|--------------|---------------|------|-----|---------|---------------- |
| news_id      | int(11)       | NO   | PRI | NULL    | auto_increment |
| title        | varchar(2000) | YES  |     | NULL    |                |
| intro        | varchar(2000) | YES  |     | NULL    |                |
| content      | text          | YES  |     | NULL    |                |
| source       | varchar(255)  | YES  |     | NULL    |                |
| tags         | varchar(255)  | YES  |     | NULL    |                |
| url          | varchar(255)  | YES  |     | NULL    |                |
| publish_time | datetime      | YES  |     | NULL    |                |


We want to add the feature of caching, because of which the queries become faster. For instance, once we do a query to get the news with news_id = 100, at the first time the sql will be sent to MySQL. The second time when somebody else wants to do the same query (or query that includes querying news with news_id = 100), the cached data should be queried (if not removed yet), and returned back to the user, instead of sending the sql again. Also we want to adjust the cache based on our needs when doing the add, update or delete.

Spring boot allows one to use the @EnableCaching annotation. By default it will use the simple in-memory cache, cache manager is the concurrentMapCacheManager. The cache is stored in jvm memory. 

Here in this demo, we are using redis to store cache data. By adding the spring-boot-starter-data-redis dependency, spring boot will auto configure RedisCacheManager as our cache manager.
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

Here we operate several CRUD operations:
```
@Service
@CacheConfig(cacheNames = "com.imcode.cms.news")
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
```
With @Cacheable annotation, pair with the key "id" will be cached into Redis when we do the query. Thus when doing the same query again, the sql won't be sent to the database again. @CachePut basically does similar things. @CacheEvict is used to delete data from cache when certain entity is removed from database.