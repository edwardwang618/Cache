package com.mycassandra.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("news")
public class News {
    @PrimaryKey
    private NewsKey key;

    @Column("content")
    private String content;

    @Column("update_time")
    private LocalDateTime updateTime;

    @Override
    public String toString() {
        return "News{" +
                "key=" + key +
                ", content='" + content + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
