package com.kyx.blog.service;

import com.kyx.blog.entity.vo.BlogMessageVoEntity;
import org.springframework.stereotype.Service;
public interface RedisService {

    /**
     *保存博客发布信息
     * @param blogMessageVoEntity
     */
    void SaveEditorBlog(BlogMessageVoEntity blogMessageVoEntity);
}
