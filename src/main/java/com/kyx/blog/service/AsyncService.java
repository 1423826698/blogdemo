package com.kyx.blog.service;

import com.kyx.blog.entity.Label;

import java.util.List;

public interface AsyncService {
        /**
         * 博客文章访问量
         */
        void updBlogLook(Long id,int look);

        /**
         * 存入数据库中用户名
         */
        void insUserName();

        /**
         * 首页博文
         */
        void insPageBlog();

        /**
         * 标签名
         * @param list
         */
        void insLabelName(List<Label> list);

        /**
         * 更新redis
         */
        void upRedis();
}
