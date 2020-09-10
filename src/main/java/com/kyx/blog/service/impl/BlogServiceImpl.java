package com.kyx.blog.service.impl;

import com.kyx.blog.entity.Blog;
import com.kyx.blog.entity.vo.BlogMessageVoEntity;
import com.kyx.blog.mapper.BlogMapper;
import com.kyx.blog.service.AsyncService;
import com.kyx.blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kyx.blog.service.RedisService;
import com.kyx.util.Constant;
import com.kyx.util.RedisOperate;
import com.kyx.util.StringAndArray;
import com.kyx.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kyx
 * @since 2020-06-05
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper,BlogMessageVoEntity> implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private RedisOperate redisOperate;
    @Autowired
    private RedisService redisService;
    @Autowired
    private AsyncService asyncService;

    @Override
    public void publishBlog(BlogMessageVoEntity blogMessageVoEntity) {

            blogMessageVoEntity.setLook(0);
            blogMessageVoEntity.setCreateTime(new TimeUtil().createCurrentTime());
            blogMessageVoEntity.setId(new TimeUtil().getLongTime());
            blogMessageVoEntity.setArticleUrl("article/"+blogMessageVoEntity.getId());
            blogMapper.insert(blogMessageVoEntity);

        redisService.SaveEditorBlog(blogMessageVoEntity);
    }

    @Override
    public BlogMessageVoEntity findBlogById(long id) {
        BlogMessageVoEntity blogMessageVoEntity =null;

        //从缓存中查询
        if (redisOperate.hasHkey(Constant.BLOG_DETAIL,String.valueOf(id))){
            if (redisOperate.hget(Constant.BLOG_DETAIL,String.valueOf(id))==null){
                return blogMessageVoEntity;
            }
            blogMessageVoEntity =(BlogMessageVoEntity) redisOperate.hget(Constant.BLOG_DETAIL,String.valueOf(id));

            //缓存中是否有博客浏览次数
            if (redisOperate.hasKey(Constant.BLOG_DETAIL+id)){
                //异步存储
                asyncService.updBlogLook(id,(int)redisOperate.incr(Constant.BLOG_DETAIL+id,1));
            }else {
                BlogMessageVoEntity  look=blogMapper.selectById(id);
                int looks =look.getLook()+1;
                redisOperate.set(Constant.BLOG_DETAIL+id,looks);
            }

        }else{
            blogMessageVoEntity = blogMapper.selectById(id);
            blogMessageVoEntity.setTagValues(StringAndArray.stringToArray(blogMessageVoEntity.getLabelValues()));
            blogMessageVoEntity.setArticleUrl("article/"+blogMessageVoEntity.getId());
            redisOperate.hset(Constant.BLOG_DETAIL,String.valueOf(id),blogMessageVoEntity);
        }
        return blogMessageVoEntity;
    }

    @Override
    public BlogMessageVoEntity updBlogByBlogId(BlogMessageVoEntity blogMessageVoEntity) {
        blogMessageVoEntity.setCreateTime(new TimeUtil().createCurrentTime());
        blogMessageVoEntity.setTagValues(StringAndArray.stringToArray(blogMessageVoEntity.getLabelValues()));
        blogMessageVoEntity.setArticleUrl("/article/"+blogMessageVoEntity.getId());
        //更新数据库
        blogMapper.updateById(blogMessageVoEntity);
        //存入缓存中(首页分页查询) -- 先删后存
        redisOperate.lremove(Constant.PAGE_BLOG,0,redisOperate.hget(Constant.BLOG_DETAIL,String.valueOf(blogMessageVoEntity.getId())));
        redisOperate.lpush(Constant.PAGE_BLOG,blogMessageVoEntity);
        // 存入缓存（博客具体详情） -- 先删后存
        redisOperate.hdel(Constant.BLOG_DETAIL,String.valueOf(blogMessageVoEntity.getId()));
        redisOperate.hset(Constant.BLOG_DETAIL,String.valueOf(blogMessageVoEntity.getId()),blogMessageVoEntity);
        return blogMessageVoEntity;
    }

    @Override
    public BlogMessageVoEntity EchoBlogByBlogId(long id) {

        BlogMessageVoEntity blogMessageVoEntity =findBlogById(id);
        blogMessageVoEntity.setTagValues(StringAndArray.stringToArray(blogMessageVoEntity.getLabelValues()));
        return blogMessageVoEntity;
    }
}
