package com.kyx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan("com.kyx.blog.mapper")
@SpringBootApplication
@EnableAsync
public class BlogdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogdemoApplication.class, args);
    }

}
