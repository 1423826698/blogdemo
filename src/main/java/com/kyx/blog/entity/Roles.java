package com.kyx.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Set;

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
@ApiModel(value="Roles对象", description="")
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标识符")
    @TableId(value = "rid", type = IdType.ID_WORKER)
    private Integer rid;

    @ApiModelProperty(value = "角色")
    private String rname;

    @TableField(exist = false)
    private Set<Permission> permissionsSet;
}
