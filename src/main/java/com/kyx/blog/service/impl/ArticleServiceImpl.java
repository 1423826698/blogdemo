package com.kyx.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kyx.blog.entity.Blog;
import com.kyx.blog.entity.vo.BlogMessageVoEntity;
import com.kyx.blog.mapper.BlogMapper;
import com.kyx.blog.service.ArticleService;
import com.kyx.blog.service.AsyncService;
import com.kyx.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    RedisOperate redisOperate;
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    AsyncService asyncService;
    @Override
    public PagedResult findAllBlog(Integer page, Integer pageSize) {
        PagedResult pagedResult =new PagedResult();
        //更新redis
        asyncService.upRedis();
        if (redisOperate.hasKey(Constant.PAGE_BLOG)){
            int start =(page-1)*pageSize;
            int stop =page*pageSize-1;
            List<BlogMessageVoEntity> range =(List<BlogMessageVoEntity>) redisOperate.range(Constant.PAGE_BLOG,start,stop);
            long length =redisOperate.llen(Constant.PAGE_BLOG);
            pagedResult.setContent(range);
            pagedResult.setPage(page);
            pagedResult.setTotal(length);
            pagedResult.setRecords(length);
        }else {
            IPage<BlogMessageVoEntity> blog =blogMapper.selectPage(new Page<>(page,pageSize),new QueryWrapper<BlogMessageVoEntity>()
                    .orderByDesc("id"));
            for (BlogMessageVoEntity b:blog.getRecords()) {
                b.setArticleUrl("article/"+b.getId());
                b.setTagValues(StringAndArray.stringToArray(b.getLabelValues()));
            }
            if (blog!=null){
               /* List<BlogMessageVoEntity> list = blogMapper.selectList(null);
                for (BlogMessageVoEntity b : list){
                    redisOperate.lpush(Constant.PAGE_BLOG,b);
                }*/
               asyncService.insPageBlog();
            }
            pagedResult.setPage(page);
            pagedResult.setTotal(blog.getPages());
            pagedResult.setContent(blog.getRecords());
            pagedResult.setRecords(blog.getTotal());
        }
        return pagedResult;
    }

    @Override
    public PagedResult findByTag(Integer pageNum ,Integer pageSize,String tag) {
        IPage<BlogMessageVoEntity> blogIPage =blogMapper.selectPage(new Page<>(pageNum,pageSize),new QueryWrapper<BlogMessageVoEntity>()
                .like("label_values", CommonUtils.UnicodeToString(tag)).orderByDesc("id"));
        for (BlogMessageVoEntity b :blogIPage.getRecords())
        {
            b.setTagValues(StringAndArray.stringToArray(b.getLabelValues()));
            b.setSpecificTag(CommonUtils.UnicodeToString(tag));
        }

        PagedResult result =new PagedResult();
        result.setPage(pageNum);
        result.setTotal(blogIPage.getPages());
        result.setRecords(blogIPage.getTotal());
        result.setContent(blogIPage.getRecords());
        return result;
    }

    @Override
    public PagedResult findByCategories() {
        return null;
    }

    @Override
    public PagedResult findByTime() {
        return null;
    }

    @Override
    public int Like() {
        return 0;
    }
    @Override
    public PagedResult AllTotal(){
        PagedResult pagedResult =new PagedResult();
        if (redisOperate.hasKey(Constant.PAGE_BLOG)){
            List<BlogMessageVoEntity> list =(List<BlogMessageVoEntity>) redisOperate.range(Constant.PAGE_BLOG,0,-1);
            pagedResult.setContent(list);

        }else {
            List<BlogMessageVoEntity> list1 = blogMapper.selectList(new QueryWrapper<BlogMessageVoEntity>().orderByDesc("id"));
            pagedResult.setContent(list1);
            if (list1 != null) {
                asyncService.insPageBlog();
            }
        }
        return pagedResult;
    }

}
