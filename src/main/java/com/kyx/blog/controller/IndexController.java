package com.kyx.blog.controller;

import com.kyx.blog.service.IndexService;
import com.kyx.util.BlogJSONResult;
import com.kyx.util.Constant;
import com.kyx.util.RedisOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    IndexService indexService;
    @Autowired
    RedisOperate redisOperate;
    /**
     * 文章总数
     * @return
     */
    @GetMapping("ArticleCounts")
    public BlogJSONResult ArticleCounts(){
        Long count =0l;
        if (!redisOperate.hasKey(Constant.BLOG_COUNT)){
            count =indexService.findArticleCounts();
        }else {
            count =redisOperate.hsize(Constant.BLOG_DETAIL);
        }
        return BlogJSONResult.ok(count);
    }

    /**
     * 标签总数
     * @return
     */
    @GetMapping("LabelsCounts")
    public BlogJSONResult LabelsCounts(){
        Integer count =0;
        if (!redisOperate.hasKey(Constant.LABEL_ALL_COUNT)){
            count =indexService.findLabelCounts();
        }else{
            count = (Integer) redisOperate.get(Constant.LABEL_ALL_COUNT);
        }
        return BlogJSONResult.ok(count);
    }

    /**
     * 留言总数
     * @return
     */
    @GetMapping("GuestCounts")
    public BlogJSONResult GuestCount(){
        int count =0;
        if (!redisOperate.hasKey(Constant.BLOG_GUEST_COUNT)){
            count =indexService.findGuestCounts();
        }else {
            count = (int) redisOperate.get(Constant.BLOG_GUEST_COUNT);
        }
        return BlogJSONResult.ok(count);
    }
}
