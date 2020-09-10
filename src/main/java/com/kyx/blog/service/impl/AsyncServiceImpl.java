package com.kyx.blog.service.impl;

import com.kyx.blog.entity.Label;
import com.kyx.blog.entity.vo.BlogMessageVoEntity;
import com.kyx.blog.mapper.BlogMapper;
import com.kyx.blog.service.AsyncService;
import com.kyx.util.Constant;
import com.kyx.util.RedisOperate;
import com.kyx.util.StringAndArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AsyncServiceImpl implements AsyncService {
    @Autowired
    RedisOperate redisOperate;
    @Autowired
    BlogMapper blogMapper;
    @Async
    @Override
    public void updBlogLook(Long id,int look) {
        BlogMessageVoEntity blogMessageVoEntity =new BlogMessageVoEntity();
        blogMessageVoEntity.setLook(look);
        blogMessageVoEntity.setId(id);
        blogMapper.updateById(blogMessageVoEntity);
    }

    @Override
    public void insUserName() {

    }
    @Async
    @Override
    public void insPageBlog() {
        List<BlogMessageVoEntity> list = blogMapper.selectList(null);
        for (BlogMessageVoEntity b: list){
            b.setTagValues(StringAndArray.stringToArray(b.getLabelValues()));
            b.setArticleUrl("article/"+b.getId());
            redisOperate.lpush(Constant.PAGE_BLOG,b);
        }

    }
    @Async
    @Override
    @Transactional
    public void insLabelName(List<Label> list) {
        for (Label label:list){
            redisOperate.lpush(Constant.LABEL_ALL,label);
        }
    }
    @Async
    @Override
    public void upRedis() {
        List<BlogMessageVoEntity> list =(List<BlogMessageVoEntity>)redisOperate.range(Constant.PAGE_BLOG,0,-1);
        for (BlogMessageVoEntity b :list){
            redisOperate.lremove(Constant.PAGE_BLOG,0,b);
        }

    }
}
