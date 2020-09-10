package com.kyx.blog.service;

import com.kyx.blog.entity.Friendurl;

import java.util.List;

public interface FriendUrlService {
    /**
     * 查询全部友情链接
     * @return
     */
    List<Friendurl> getAllFriend();

    /**
     * 增加友联
     * @param friendurl
     */
    void addFriend(Friendurl friendurl);

    /**
     * 删除友联
     * @param id
     */
    void deleteFriend(Long id);
}
