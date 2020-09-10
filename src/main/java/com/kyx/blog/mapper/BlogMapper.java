package com.kyx.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kyx.blog.entity.vo.BlogMessageVoEntity;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kyx
 * @since 2020-06-05
 */
@Repository
public interface BlogMapper extends BaseMapper<BlogMessageVoEntity> {

}
