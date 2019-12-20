package com.mycassandra.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("news")
public class News implements Serializable {

    @PrimaryKey
    private NewsKey key;

    @Column("content")
    private String content;

    @Column("update_time")
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
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
