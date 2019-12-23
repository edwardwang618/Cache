package com.imcode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.imcode.*.mapper")
@EnableCaching
public class IBootApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(IBootApplication.class, args);
    }
    
}
