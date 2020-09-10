package com.kyx.blog.controller;

import com.kyx.blog.entity.Repguest;
import com.kyx.blog.entity.Users;
import com.kyx.blog.service.UserService;
import com.kyx.util.BlogJSONResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("userInfo")
    public BlogJSONResult userInfo(){

        return BlogJSONResult.ok();
    }
    /**
     * 是否登入
     * @param request
     * @return
     */
    @GetMapping("isLogin")
    public BlogJSONResult isLogin(HttpServletRequest request){
        Users users = (Users) request.getSession().getAttribute("user");
        if (users==null){
            return BlogJSONResult.errorTokenMsg("用户不存在");
        }

        return BlogJSONResult.ok();
    }
    @GetMapping("article/isLogin")
    public BlogJSONResult isLogin2(HttpServletRequest request){
        Users users = (Users) request.getSession().getAttribute("user");
        if (users==null){
            return BlogJSONResult.errorTokenMsg("用户不存在");
        }

        return BlogJSONResult.ok();
    }
    /**
     * 退出登入
     * @param request
     * @return
     */
    @GetMapping("logout")
    public BlogJSONResult logout(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        if (subject!=null){
            request.getSession().invalidate();
            subject.logout();
        }
        return BlogJSONResult.ok();
    }

    /**
     *我的留言（用户）
     * @param name
     * @return
     */
    @GetMapping("getUserGuest")
    public BlogJSONResult getUserGuest(@RequestParam("name") String name){
        return BlogJSONResult.ok(userService.allReGuest(name));
    }

    /**
     * 部分消息已读（用户）
     * @param id
     * @return
     */
    @GetMapping("partMessRead")
    public BlogJSONResult partMessRead(@RequestParam("id") Long id){
        userService.upNotReGuestUser(id);
        System.out.println(id);
        return BlogJSONResult.ok();
    }

    /**
     * 全部消息已读
     * @param name
     * @return
     */
    @GetMapping("allMessRead")
    public BlogJSONResult allMessRead(@RequestParam("")String name){
        userService.upGuest(name.trim());
    return BlogJSONResult.ok();
    }

    /**
     * 获取全部用户评论
     * @param name
     * @return
     */
    @GetMapping("allComment")
    public BlogJSONResult allComment(@RequestParam("name")String name){
        return BlogJSONResult.ok(userService.allComment(name.trim()));
    }
    @GetMapping("deleteComment")
    public BlogJSONResult deleteComment(@RequestParam("rid") Long id){
        userService.upBlogNotComment(id);
        return BlogJSONResult.ok();
    }
}
