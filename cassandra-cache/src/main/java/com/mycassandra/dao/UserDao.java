package com.mycassandra.dao;

import com.mycassandra.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
@CacheConfig(cacheNames = "mycas.user")
public class UserDao {

    @Autowired
    private CassandraTemplate template;

    @Cacheable
    public List<User> getAll() {
        return template.select("select * from users", User.class);
    }

    @CachePut(key = "#user.userid")
    public User insert(User user) {
        return template.insert(user);
    }

    @Cacheable(key = "#id")
    public User getOneById(Integer id) {
        simulateSlowService();
        return template.selectOneById(id, User.class);
    }

    @Cacheable(key = "#user.userid")
    public User setOneById(User user) {
        return template.update(user);
    }

    private void simulateSlowService() {
        try {
            long time = 2000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
