package com.kyx.blog.service;

import com.kyx.blog.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kyx.blog.entity.vo.BlogMessageVoEntity;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kyx
 * @since 2020-06-05
 */
public interface BlogService extends IService<BlogMessageVoEntity> {

    /**
     * 博客发布
     */

    void publishBlog(BlogMessageVoEntity blogMessageVoEntity);

    /**
     * 通过id查询文章详情
     * @param id
     * @return
     */
    BlogMessageVoEntity findBlogById(long id);

    /**
     *编辑文章
     * @param blogMessageVoEntity
     * @return
     */
    BlogMessageVoEntity updBlogByBlogId(BlogMessageVoEntity blogMessageVoEntity);

    /**
     *  修改回显文章相关信息
     * @param id
     * @return
     */
    BlogMessageVoEntity EchoBlogByBlogId(long id);
}
