package com.kyx;

import com.kyx.blog.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogdemoApplicationTests {

    @Test
    void contextLoads() {

    }

    @Autowired
    private MailService mailService;

    @Test
    public void sendMail(){
        mailService.sendSimpleEmail("ok123456","测试","1423826698@qq.com");
    }
}
