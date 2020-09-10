package com.kyx.blog.service;

public interface MailService {
    void sendSimpleEmail(String context,String subject,String to);
}
