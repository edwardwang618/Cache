#Cassandra query, Spring Cache implemented with Redis

The table structure is as such:
```$xslt
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
the news will be ordered increasingly based on the news_id.

The following table is saved in the cassandra.
```
 source    | news_id | content          | update_time
-----------+---------+------------------+---------------------------------
 Business1 |       1 | Business1 starts | 2019-12-20 16:41:11.355000+0000
 Business2 |       1 | Business2 starts | 2019-12-20 03:07:37.076000+0000
```

We can run the following test to see how cache works:
```
@Test
void getByKey() {
    NewsKey key = new NewsKey("Business2", 1);
    System.out.println(newsService.getByKey(key));
    System.out.println(newsService.getByKey(key));

}
```
Since the cache we are using is redis. When we do the first query,
the object we get from cassandra will be serialized into redis, the key being 
```
cassandra.news::NewsKey(source=Business2, newsID=1)
``` 
and the value is the json format string of the object:
```json
["com.mycassandra.entity.News",{"key":["com.mycassandra.entity.NewsKey",{"source":"Business2","newsID":1}],"content":"Business2 starts","updateTime":"12-19-2019 21:07:37"}]
```

By query the Redis, we can see the cache works well.

We can even add more features into cache, for instance, we can set 
the expiring time of the cache, by call the "entryTtl(Duration.ofMinutes(2L)"
of config. Here we set the expiring time to be 2 minutes.