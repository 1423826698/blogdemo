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
@ApiModel(value="Blog对象", description="")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标识符")
    @TableId(value = "id")

    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "正文")
    private String text;

    @ApiModelProperty(value = "标签")
    private String labelValues;

    @ApiModelProperty(value = "文章类型")
    private String selectType;

    @ApiModelProperty(value = "博客分类")
    private String selectCategories;

    @ApiModelProperty(value = "文章等级")
    private Integer selectGrade;

    @ApiModelProperty(value = "原文章作者")
    private String originalAuthor;

    @ApiModelProperty(value = "文章（0-公开  1-私密）")
    private String message;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "点赞")
    private Integer likes;

    @ApiModelProperty(value = "作者名字")
    private String name;

    @ApiModelProperty(value = "文章摘要")
    private String articleTabled;

    @ApiModelProperty(value = "浏览次数")
    private Integer look;


}
