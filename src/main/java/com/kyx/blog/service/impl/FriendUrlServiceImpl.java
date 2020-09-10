package com.kyx.blog.service.impl;

import com.kyx.blog.entity.Friendurl;
import com.kyx.blog.mapper.FriendUrlMapper;
import com.kyx.blog.service.FriendUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FriendUrlServiceImpl implements FriendUrlService {
    @Autowired
    FriendUrlMapper friendUrlMapper;
    @Override
    public List<Friendurl> getAllFriend() {
        return friendUrlMapper.selectList(null);
    }

    @Override
    public void addFriend(Friendurl friendurl) {
        friendUrlMapper.insert(friendurl);
    }

    @Override
    public void deleteFriend(Long id) {
        friendUrlMapper.deleteById(id);
    }
}
