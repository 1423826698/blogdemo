package com.kyx.blog.controller;

import com.kyx.blog.entity.Label;
import com.kyx.blog.service.LabelService;
import com.kyx.util.BlogJSONResult;
import com.kyx.util.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tag")
public class LabelController {

    @Autowired
    private LabelService labelService;

    /**
     * 标签云
     * @return
     */

    @GetMapping("/tags")
    public BlogJSONResult tags(){
        List<Label> list =labelService.queryList();
        return BlogJSONResult.ok(list);
    }



}
