package com.kyx.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private String id;

    private String username;

    private String password;

    private int roleId;
    @TableField(exist = false)
    private Set<Roles> roles;
}
