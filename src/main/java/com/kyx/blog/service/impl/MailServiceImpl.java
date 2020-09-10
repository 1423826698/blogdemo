package com.kyx.blog.service.impl;

import com.kyx.blog.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSenderImpl javaMailSender;
    @Value("${spring.mail.username}")
    private String from;
    @Override
    public void sendSimpleEmail(String context,String subject,String to) {
        SimpleMailMessage simpleMailMessage =new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setText(context);
        simpleMailMessage.setSubject(subject);

        try {
            javaMailSender.send(simpleMailMessage);
            log.info("邮件发送成功");
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}
