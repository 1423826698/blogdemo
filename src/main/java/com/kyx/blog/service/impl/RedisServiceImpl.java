package com.kyx.blog.service.impl;

import com.kyx.blog.entity.vo.BlogMessageVoEntity;
import com.kyx.blog.service.RedisService;
import com.kyx.util.Constant;
import com.kyx.util.RedisOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    RedisOperate redisOperate;
    @Override
    public void SaveEditorBlog(BlogMessageVoEntity blogMessageVoEntity) {
        //存入缓存中（首页分页查询）
            redisOperate.lpush(Constant.PAGE_BLOG,blogMessageVoEntity);
        //存入缓存中 （博客具体详情）
            redisOperate.hset(Constant.BLOG_DETAIL,String.valueOf(blogMessageVoEntity.getId()),blogMessageVoEntity);
        //文章浏览次数
            redisOperate.set(Constant.BLOG_DETAIL+blogMessageVoEntity.getId(),0);
    }
}
