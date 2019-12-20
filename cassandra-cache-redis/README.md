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
the news will be ordered ascendingly based on the news_id.