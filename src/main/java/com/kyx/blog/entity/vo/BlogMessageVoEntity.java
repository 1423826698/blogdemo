package com.kyx.blog.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kyx.blog.entity.Blog;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "blog")
public class BlogMessageVoEntity extends Blog implements Serializable {

    private static final long serialVersionUID = -26942105678437474L;
    /**
     *html文章
     */
    @TableField(exist = false)
    private String articleHtmlContent;

    /**
     * 标签
     */
    @TableField(exist = false)
    private String[] tagValues;

    /**
     * 文章的的地址
     */
    @TableField(exist = false)
    private String articleUrl;

    /**
     * 规定标签
     */
    @TableField(exist = false)
    private String specificTag;
}
