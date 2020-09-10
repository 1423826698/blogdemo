package com.kyx.blog.service;

import com.kyx.util.PagedResult;

public interface RootAdminService {
    /**
     * 查询全部用户
     * @return
     */
    PagedResult findAllUsers(Integer pageSize, Integer currentPage);

    /**
     * 查询全部博客
     * @return
     */
    PagedResult findAllBlog(Integer pageSize,Integer currentPage);

    /**
     * 查询全部友情链接
     * @return
     */
    PagedResult findAllFriendUrl();

    /**
     * 删除用户
     * @return
     */
    int deleteUser(String name);

    /**
     * 删除博客
     * @return
     */
    int deleteBlog(Long id);

    /**
     * 删除友情链接
     * @return
     */
    int deleteFriendUrl();

    /**
     * 增加友情链接
     * @return
     */
    int addFriendUrl();
}
