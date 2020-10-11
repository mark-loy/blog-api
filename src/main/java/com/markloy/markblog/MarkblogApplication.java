package com.markloy.markblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.markloy.markblog.mapper")
@SpringBootApplication
public class MarkblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarkblogApplication.class, args);
    }

}
