package com.kyx.blog.service.impl;

import com.kyx.blog.mapper.IndexMapper;
import com.kyx.blog.service.IndexService;
import com.kyx.util.Constant;
import com.kyx.util.RedisOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    IndexMapper indexMapper;
    @Autowired
    RedisOperate redisOperate;
    @Override
    public Long findArticleCounts() {
        long articlecount =indexMapper.findArticleCounts();
        redisOperate.set(Constant.BLOG_COUNT,articlecount);
        return articlecount;
    }

    @Override
    public int findLabelCounts() {
        int labelcount =indexMapper.findLabelCounts();
        redisOperate.set(Constant.LABEL_ALL_COUNT,labelcount);
        return labelcount;
    }

    @Override
    public int findGuestCounts() {
        int guestcount =indexMapper.findGuestCounts()+indexMapper.findGuestRepCounts();
        redisOperate.set(Constant.BLOG_GUEST_COUNT,guestcount);
        return guestcount;
    }
}
