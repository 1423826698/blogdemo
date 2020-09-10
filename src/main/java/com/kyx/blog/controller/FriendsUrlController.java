package com.kyx.blog.controller;

import com.kyx.blog.entity.Blog;
import com.kyx.blog.entity.Friendurl;
import com.kyx.blog.service.FriendUrlService;
import com.kyx.util.BlogJSONResult;
import com.kyx.util.TimeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FriendsUrlController {
    @Autowired
    FriendUrlService friendUrlService;
    /**
     * 查询所有的友情链接
     * @return
     */
    @GetMapping("allFriendUrl")
    public BlogJSONResult allFriendUrl(){
        return BlogJSONResult.ok(friendUrlService.getAllFriend());
    }
    @PostMapping("addFriendUrl")
    public BlogJSONResult addFriendUrl(@RequestBody Friendurl friendurl){
        Subject subject =SecurityUtils.getSubject();
        if (subject.hasRole("admin")){
            TimeUtil timeUtil =new TimeUtil();
            friendurl.setId(timeUtil.getLongTime());
            friendUrlService.addFriend(friendurl);
        }
        return BlogJSONResult.ok();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("deleteFriend")
    public BlogJSONResult deleteFriend(@RequestParam("id") Long id){
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("admin")){
            friendUrlService.deleteFriend(id);
        }
        return BlogJSONResult.ok();
    }

}
