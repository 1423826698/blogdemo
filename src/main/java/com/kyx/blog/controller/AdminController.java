package com.kyx.blog.controller;

import com.kyx.blog.service.RootAdminService;
import com.kyx.util.BlogJSONResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    RootAdminService rootAdminService;
    /**
     * 用户的信息
     * @return
     */

    @GetMapping("getUsers")
    public BlogJSONResult getUsers(@RequestParam("pageSize")Integer pageSize,@RequestParam("currentPage") Integer currentPage){

        return BlogJSONResult.ok( rootAdminService.findAllUsers(pageSize,currentPage));
    }

    /**
     * 删除用户
     */
    @GetMapping("deleteUser")
    public BlogJSONResult deleteUser(@RequestParam("Name")String name){
        Subject subject =SecurityUtils.getSubject();
        if (subject.hasRole("admin")){
            rootAdminService.deleteUser(name);
        }
        return BlogJSONResult.ok();
    }

    /**
     * 获取博客信息
     * @param pageSize
     * @param currentPage
     * @return
     */
    @GetMapping("getBlog")
    public BlogJSONResult getBlog(@RequestParam("pageSize")Integer pageSize,@RequestParam("currentPage") Integer currentPage){
        return BlogJSONResult.ok(rootAdminService.findAllBlog(pageSize,currentPage));
    }
    @GetMapping("delBlog")
    public BlogJSONResult delBlog(@RequestParam("Id")Long id){
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("admin")){
            rootAdminService.deleteBlog(id);
        }
        return BlogJSONResult.ok();
    }
}
