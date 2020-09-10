package com.kyx.blog.service;

import com.kyx.blog.entity.Label;

import java.util.List;

public interface LabelService {

    /**
     *  新增标签
     */
    int insLabel(String[] label);

    /**
     * 查询标签
     * @return
     */
    List<Label> queryList();
}
