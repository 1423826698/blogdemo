package com.kyx.blog.service;

import com.kyx.blog.entity.Comment;
import com.kyx.blog.entity.Guest;
import com.kyx.blog.entity.Repguest;
import com.kyx.blog.entity.Users;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {
    /**
     * 通过用户名查询
     * @param username 用户名
     * @return
     */
    Users findUserBy(String username);
    /**
     * 个人信息
     * @param name
     * @return
     */
    Users userMessage(String name);

    /**
     *  我的留言（网站）
     * @return
     */
    List<Guest> allGuest();

    /**
     * 我的留言（用户）
     * @param name
     * @return
     */
    List<Repguest> allReGuest(String name);

    /**
     *  全部消息已读（留言 -用户）
     * @param name
     * @return
     */
    List<Repguest> upGuest(String name);

    /**
     *  部分消息已读（管理员）
     * @param id
     * @return
     */
    int upNotGuestSuper(Long id);

    /**
     *  部分消息已读（用户）
     * @param id
     * @return
     */
    int upNotReGuestUser(Long id);

    /**
     *  我的博客评论
     * @return
     */
    List<Repguest> allComment(String name);

    /**
     *  我的博客评论部分消息已读
     * @param id
     * @return
     */
    int upBlogNotComment(Long id);

    /**
     *  反馈（管理员）
     * @param name
     * @return
     */
    int MessageNotRead(String name);

    /**
     *  消息未读
     * @param name
     * @return
     */
    int MessageNotReadUser(String name);
}
