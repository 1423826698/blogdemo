package com.kyx.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kyx.blog.entity.Comment;
import com.kyx.blog.entity.Guest;
import com.kyx.blog.entity.Repguest;
import com.kyx.blog.entity.Users;
import com.kyx.blog.mapper.GuestMapper;
import com.kyx.blog.mapper.RepGuestMapper;
import com.kyx.blog.mapper.UsersMapper;
import com.kyx.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UsersMapper usersMapper;
    @Autowired
    RepGuestMapper repGuestMapper;
    @Autowired
    GuestMapper guestMapper;
    @Override
    public Users findUserBy(String username) {
        return usersMapper.findByName(username);
    }

    @Override
    public Users userMessage(String name) {

        return usersMapper.selectOne(new QueryWrapper<Users>().eq("username",name));
    }

    @Override
    public List<Guest> allGuest() {
        return guestMapper.selectList(null);
    }

    @Override
    public List<Repguest> allReGuest(String name) {
        return repGuestMapper.selectList(new QueryWrapper<Repguest>()
                .eq("guest_name",name.trim())
                .orderByDesc("rid"));
    }

    @Override
    public List<Repguest> upGuest(String name) {
        List<Long> id =repGuestMapper.findNotReadGuest(name);

        if (id.size()==0){
            return null;
        }
        if (repGuestMapper.updGuestIsRead(id)>0){
            return allReGuest(name);
        }
        return null;
    }

    @Override
    public int upNotGuestSuper(Long id) {
        return guestMapper.upNotReadGuest(id);
    }

    @Override
    public int upNotReGuestUser(Long id) {
        Repguest repguest =new Repguest();
        repguest.setRid(id);
        repguest.setRisRead(0);
        return repGuestMapper.updateById(repguest);
    }

    @Override
    public List<Repguest> allComment(String name) {
        return repGuestMapper.selectList(new QueryWrapper<Repguest>().eq("rep_name",name).orderByDesc("rid"));
    }

    @Override
    public int upBlogNotComment(Long id) {
        return repGuestMapper.delete(new QueryWrapper<Repguest>().eq("rid",id));
    }

    @Override
    public int MessageNotRead(String name) {
        return 0;
    }

    @Override
    public int MessageNotReadUser(String name) {
        return 0;
    }
}
