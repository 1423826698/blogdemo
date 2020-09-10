package com.kyx.blog.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexMapper {
    @Select("select count(*) from blog")
    Long findArticleCounts();
    @Select("select count(*) from label")
    int findLabelCounts();
    @Select("select count(*) from guest")
    Integer findGuestCounts();
    @Select("select count(*) from repguest")
    Integer findGuestRepCounts();
}
