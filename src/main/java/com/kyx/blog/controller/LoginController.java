package com.kyx.blog.controller;

import com.kyx.blog.entity.Users;
import com.kyx.util.BlogJSONResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @GetMapping("loginUser")
 public BlogJSONResult loginUser(@RequestParam("username") String name,
                                 @RequestParam("password") String password,
                                 HttpSession session){
        UsernamePasswordToken usernamePasswordToken =new UsernamePasswordToken(name,password);
        Subject subject = SecurityUtils.getSubject();

        try{
            subject.login(usernamePasswordToken);
            Users user = (Users) subject.getPrincipal();
            user.setPassword("");
            user.setUsername(name);
            session.setAttribute("user",user);
            }catch (Exception e){
                return BlogJSONResult.errorTokenMsg("输入的用户名或密码错误");
            }
        return BlogJSONResult.ok();
 }

}
