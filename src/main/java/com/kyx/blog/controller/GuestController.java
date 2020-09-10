package com.kyx.blog.controller;

import com.kyx.blog.entity.Guest;
import com.kyx.blog.entity.Repguest;
import com.kyx.blog.entity.Users;
import com.kyx.blog.service.GuestService;
import com.kyx.util.BlogJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class GuestController {
    @Autowired
    GuestService guestService;

    /**
     * 获取全部留言
     * @return
     */
    @GetMapping("allGuest")
    public BlogJSONResult allGuest(){
        return BlogJSONResult.ok(guestService.AllGuest());
    }

    /**
     * 新增插入留言
     * @param guest
     * @param request
     * @return
     */
    @PostMapping("insertGuest")
    public BlogJSONResult insertGuest(@RequestBody Guest guest,
                                      HttpServletRequest request){
        Users user = (Users) request.getSession().getAttribute("user");
        guest.setUserId(user.getId());
        guest.setAuthorName(user.getUsername());
        List<Guest> guests =guestService.insertGuest(guest);
        return BlogJSONResult.ok(guests);
    }

    /**
     *  新增插入留言评论
     * @param repguest
     * @param request
     * @return
     */
    @PostMapping("insertRepGuest")
    public BlogJSONResult insertRepGuest(@RequestBody Repguest repguest,
                                         HttpServletRequest request){
        Users user = (Users) request.getSession().getAttribute("user");
        repguest.setRguestId(user.getId());
        repguest.setRepName(user.getUsername());
        List<Guest> repguests =guestService.insertRepGuest(repguest);
        return  BlogJSONResult.ok(repguests);
    }

}
