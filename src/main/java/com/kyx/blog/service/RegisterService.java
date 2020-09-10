package com.kyx.blog.service;

import com.kyx.blog.entity.Users;

public interface RegisterService {
    /**
     * 添加用户
     * @return
     */
    int insUser(Users users);

    /**
     * 邮箱检测
     * @return
     */
    int findByEmail();
}
