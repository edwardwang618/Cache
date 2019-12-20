package com.mycassandra.entity;

import com.google.common.base.Objects;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;

@Data
@PrimaryKeyClass
public class NewsKey implements Serializable {

    @PrimaryKeyColumn(name = "source", type = PrimaryKeyType.PARTITIONED)
    private String source;

    @PrimaryKeyColumn(name = "news_id", type = PrimaryKeyType.CLUSTERED)
    private Integer newsID;

    public NewsKey(final String source, final Integer newsID) {
        this.source = source;
        this.newsID = newsID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsKey newsKey = (NewsKey) o;
        return Objects.equal(getSource(), newsKey.getSource()) &&
                Objects.equal(getNewsID(), newsKey.getNewsID());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getSource(), getNewsID());
    }
}
