package com.mycassandra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CassandraCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(CassandraCacheApplication.class, args);
    }

}
