package com.kyx.blog.controller;

import com.kyx.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Controller
public class RedirectionController {
    @GetMapping("article/{articleUrl}")
    public String ArticleUrl(@PathVariable("articleUrl") long path,
                             HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setHeader("articleId", String.valueOf(path));
        return "show";
    }

    @GetMapping("/archive")
    public String Archive() {

        return "archive";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        String url =request.getHeader("Referer");
        System.out.println("url"+url);
        if (StringUtils.isEmpty(url)){
                return "login";
        }
        if (!url.contains("register") && !url.contains("findPwd") && !url.contains("login")) {
            //保存跳转页面的url
            request.getSession().setAttribute("lastUrl", url);
        }else {
            request.getSession().removeAttribute("lastUrl");
        }
        return "login";

    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
    @GetMapping("/editor")
    public String editor(HttpServletRequest request){
        if (!"".equals(request.getParameter("id"))){
                request.getSession().setAttribute("id",request.getParameter("id"));
        }
        return "editor";
    }
    @GetMapping("/friend")
    public String FriendsUrl(){
        return "friend";
    }
    @GetMapping("user")
    public String User(){
        return "user";
    }
    @GetMapping("guest")
    public String guest(){
        return "guest";
    }
    @GetMapping("dem2")
    public String dem2(){
        return "dem2";
    }
    @GetMapping("lastUrl")
    public String lastUrl(HttpServletRequest request,HttpServletResponse response){
        Object url = request.getSession().getAttribute("lastUrl");
        if (StringUtils.isNotEmpty(String.valueOf(url))){
            response.setHeader("lastUrl",String.valueOf(url));
            return "lastUrl";
        }else {
            return "index";
        }
    }

    @GetMapping("tag")
    public String tags(HttpServletRequest request,HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            System.out.println("TAG=="+request.getParameter("tag"));
            response.setHeader("tag", CommonUtils.stringToUnicode(request.getParameter("tag")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "tag";
    }

    @GetMapping("update")
    public String update(){
        return "update";
    }
}