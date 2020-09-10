package com.kyx.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kyx.blog.entity.Blog;
import com.kyx.blog.entity.Users;
import com.kyx.blog.entity.vo.BlogMessageVoEntity;
import com.kyx.blog.mapper.BlogMapper;
import com.kyx.blog.mapper.UsersMapper;
import com.kyx.blog.service.RootAdminService;
import com.kyx.util.Constant;
import com.kyx.util.PagedResult;
import com.kyx.util.RedisOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RootAdminServiceImpl implements RootAdminService {
    @Autowired
    UsersMapper usersMapper;
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    RedisOperate redisOperate;
    @Override
    public PagedResult findAllUsers(Integer pageSize, Integer currentPage) {
        PagedResult result =new PagedResult();
        IPage<Users> usersIPage =usersMapper.selectPage(new Page<>(currentPage,pageSize),new QueryWrapper<Users>().orderByDesc("id"));
        result.setTotal(usersIPage.getPages());
        result.setContent(usersIPage.getRecords());
        result.setPage(currentPage);
        result.setRecords(usersIPage.getTotal());
        return result;
    }

    @Override
    public PagedResult findAllBlog(Integer pageSize,Integer currentPage) {
        PagedResult result =new PagedResult();
        IPage<BlogMessageVoEntity> page = blogMapper.selectPage(new Page<>(currentPage,pageSize),new QueryWrapper<BlogMessageVoEntity>().orderByDesc("id"));
        result.setContent(page.getRecords());
        result.setTotal(page.getPages());
        result.setRecords(page.getTotal());
        result.setPage(currentPage);
        return result;
    }

    @Override
    public PagedResult findAllFriendUrl() {
        return null;
    }

    @Override
    public int deleteUser(String name) {

        return usersMapper.delete(new QueryWrapper<Users>().eq("username",name));
    }

    @Override
    public int deleteBlog(Long id) {
        //删除首页
        redisOperate.lremove(Constant.PAGE_BLOG,0,redisOperate.hget(Constant.BLOG_DETAIL,String.valueOf(id)));
        //删除文章详情
        redisOperate.hdel(Constant.BLOG_DETAIL,String.valueOf(id));
        //删除文章浏览量
        redisOperate.del(Constant.BLOG_DETAIL+id);
        return blogMapper.deleteById(id);
    }

    @Override
    public int deleteFriendUrl() {
        return 0;
    }

    @Override
    public int addFriendUrl() {
        return 0;
    }
}
