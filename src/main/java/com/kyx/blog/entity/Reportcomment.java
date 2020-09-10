package com.kyx.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author kyx
 * @since 2020-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Reportcomment对象", description="")
public class Reportcomment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "回复评论id")
    @TableId(value = "rid", type = IdType.ID_WORKER)
    private Long rid;

    @ApiModelProperty(value = "评论文章id")
    private Long commentId;

    @ApiModelProperty(value = "内容")
    private String repMess;

    @ApiModelProperty(value = "回复者id")
    private String reportedId;

    @ApiModelProperty(value = "创建时间")
    private String rcreateTime;

    @ApiModelProperty(value = "1 -- 未读  0  -- 已读")
    private Integer risRead;

    @ApiModelProperty(value = "回复者id")
    private String repName;

    @ApiModelProperty(value = "被评论者名字")
    private String comName;


}
