package com.kyx.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kyx.blog.entity.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersMapper extends BaseMapper<Users> {
    Users findByName(String name);
}
