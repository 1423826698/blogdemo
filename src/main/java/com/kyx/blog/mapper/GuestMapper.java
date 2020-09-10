package com.kyx.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kyx.blog.entity.Guest;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestMapper extends BaseMapper<Guest> {
    List<Guest> allGuest();

    @Update("update guest set is_read = 0 where id = #{arg0}")
    int  upNotReadGuest(Long id);
}
