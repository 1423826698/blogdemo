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
@ApiModel(value="Repfriend对象", description="")
public class Repfriend implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "rid", type = IdType.ID_WORKER)
    private Long rid;

    @ApiModelProperty(value = "留言条id")
    private Long friendId;

    @ApiModelProperty(value = "正文")
    private String repMess;

    @ApiModelProperty(value = "评论者id")
    private String rfriendId;

    @ApiModelProperty(value = "创建时间")
    private String rcreateTime;

    @ApiModelProperty(value = "1 - 未读  0 - 已读")
    private Integer risRead;

    @ApiModelProperty(value = "评论名称")
    private String repName;

    @ApiModelProperty(value = "被评论名称")
    private String friendName;


}
