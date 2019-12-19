package com.mycassandra.dao;


import com.mycassandra.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void getAllTest() {
        System.out.println(userDao.getAll());
    }

    @Test
    public void insertTest() {
        userDao.insert(new User(4, "Yao", "Ning"));
    }

    @Test
    public void getOneByIdTest() {
        System.out.println(userDao.getOneById(3));
        System.out.println(userDao.getOneById(3));
        System.out.println(userDao.getOneById(3));
    }

    @Test
    public void setOneByIdTest() {
        userDao.setOneById(new User(4, "Yao", "King"));
        System.out.println(userDao.getOneById(4));
    }
}