package com.adai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * author Adai
 * create 2019-02-21  22:39
 */
@SpringBootApplication
@MapperScan({"com.adai.dao"})
public class CommentsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommentsApplication.class, args);
    }
}
