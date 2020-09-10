package com.kyx.blog.service.impl;

import com.kyx.blog.entity.Users;
import com.kyx.blog.mapper.UsersMapper;
import com.kyx.blog.service.RegisterService;
import com.kyx.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    UsersMapper usersMapper;
    @Override
    public int insUser(Users users) {
    users.setId(UUID.randomUUID().toString());
    users.setRoleId(2);
    users.setPassword(String.valueOf(Md5.md5(users.getUsername(),users.getPassword())));
        return usersMapper.insert(users);
    }

    @Override
    public int findByEmail() {
        return 0;
    }
}
