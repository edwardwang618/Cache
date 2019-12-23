#Cassandra with Spring Cache

The table structure is as such:
```$cassandraql
CREATE TABLE sample.news (
    source text,
    news_id int,
    content text,
    update_time timestamp,
    PRIMARY KEY (source, news_id)
) WITH CLUSTERING ORDER BY (news_id ASC)
```

The source of the news "source" is the partition key of this table, 
while the news_id is the clustering key of the table. Within each partition, 
the news will be ordered in ascending way based on the news_id.

The cache here is the default one, namely the ConcurrentHashMap:
```properties
spring.cache.type=simple
```

To check the functionality, we add the "simulateSlowService()" to sleep the thread.
If cache works well, when we query the same item twice, the second query should go
to the cache and will not sleep another 2 sec.
```java
@Override
@Cacheable(key = "#key")
public News getByKey(NewsKey key) {
    simulateSlowService();  // sleep 2 sec
    return newsRepository.findById(key).orElse(null);
}
```

we can run the following test in 
test.java.com.mycassandra.service.NewsServiceImplTest:
```java
@Test
void getByKey() {
    NewsKey key = new NewsKey("Business1", 1);
    System.out.println(newsService.getByKey(key));
    System.out.println(newsService.getByKey(key));
}
```

The table is as such:
```cassandraql
source    | news_id | content          | update_time
-----------+---------+------------------+---------------------------------
 Business1 |       1 | Business1 starts | 2019-12-20 16:41:11.355000+0000
 Business2 |       1 | Business2 starts | 2019-12-20 03:07:37.076000+0000
```

By running the test we can see the secondly succeeds immediately after the first one,
which suggests cache works.