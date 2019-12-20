#Cassandra with Spring Cache

```$xslt
CREATE TABLE sample.news (
    source text,
    news_id int,
    content text,
    update_time timestamp,
    PRIMARY KEY (source, news_id)
) WITH CLUSTERING ORDER BY (news_id ASC)
```

