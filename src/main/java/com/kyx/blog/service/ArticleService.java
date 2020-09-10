package com.kyx.blog.service;

import com.kyx.util.PagedResult;
import org.springframework.stereotype.Service;

public interface ArticleService {
    /**
     *分页查询
     * @return
     */
    PagedResult findAllBlog(Integer page,Integer pageSize);

    /**
     *通过标签查询文章
     * @return
     */
    PagedResult findByTag(Integer pageNum ,Integer pageSize,String tag);

    /**
     * 通过归档查询
     * @return
     */
    PagedResult findByCategories();

    /**
     *通过时间查询
     * @return
     */
    PagedResult findByTime();

    /**
     * 点赞人数
     * @return
     */
    int Like();

    /**
     * 所有文章
     * @return
     */
    PagedResult AllTotal();

}
