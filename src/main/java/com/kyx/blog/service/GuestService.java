package com.kyx.blog.service;

import com.kyx.blog.entity.Guest;
import com.kyx.blog.entity.Repguest;

import java.util.List;

public interface GuestService {
    /**
     * 查询全部的留言
     * @return
     */
    List<Guest> AllGuest();

    /**
     * 新增留言
     * @param guest
     * @return
     */
    List<Guest> insertGuest(Guest guest);

    /**
     *  新增留言评论
     * @param guest
     * @return
     */
    List<Guest> insertRepGuest(Repguest guest);


}
