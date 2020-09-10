package com.kyx.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kyx.blog.entity.Repguest;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RepGuestMapper extends BaseMapper<Repguest> {
    int updGuestIsRead(List<Long> rid);
    @Select("select rid from repguest where guest_name = #{arg0} and ris_read =1 ")
    List<Long> findNotReadGuest(String name);
}
