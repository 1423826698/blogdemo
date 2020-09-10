package com.kyx.blog.controller;

import com.kyx.blog.entity.Users;
import com.kyx.blog.service.MailService;
import com.kyx.blog.service.RegisterService;
import com.kyx.util.BlogJSONResult;
import com.kyx.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {
    @Autowired
    MailService mailService;
    @Autowired
    RegisterService registerService;
    String code1;
    /**
     * 注册
     * @param users
     * @return
     */
    @PostMapping("register")
    public BlogJSONResult register(@RequestBody Users users){

        registerService.insUser(users);
        return  BlogJSONResult.ok();
    }

    /**
     * 获取验证码
     * @return
     */
    @GetMapping("getCode")
    public BlogJSONResult getCode(@RequestParam("userName") String mail){
         code1 = new CommonUtils().verificationCode();
        mailService.sendSimpleEmail(code1,"欢迎来到 没人要的程序员の博客",mail);
        return BlogJSONResult.ok();
    }

    /**
     * 判断验证码是否正确
     * @return
     */
    @GetMapping("judgeCode")
    public String judgeCode(@RequestParam("verifCode") String code) {
        System.out.println(code1.substring(5)+"="+code);
        System.out.println(Integer.valueOf(code) == Integer.valueOf(code1.substring(5)));
        if (Integer.valueOf(code).equals(Integer.valueOf(code1.substring(5)))) {
            return new BlogJSONResult(500,"验证成功","").getMsg();
        } else {
            System.out.println(Integer.valueOf(code)+"-"+Integer.valueOf(code1.substring(5)));
            return new BlogJSONResult(500,"验证失败","").getMsg();
        }
    }
}
