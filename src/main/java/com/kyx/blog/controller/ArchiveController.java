package com.kyx.blog.controller;

import com.kyx.blog.service.ArticleService;
import com.kyx.blog.service.BlogService;
import com.kyx.util.BlogJSONResult;
import com.kyx.util.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArchiveController {
    @Autowired
    ArticleService articleService;
    @GetMapping("getArchiveArticle")
    public BlogJSONResult ArticleTime(@RequestParam("page") int page,@RequestParam("rows") int rows){

       PagedResult p= articleService.findAllBlog(page,rows);
       return BlogJSONResult.ok(p);

    }
    @GetMapping("getArchiveTotal")
    public BlogJSONResult ArticleTotal(){
        PagedResult p1 =articleService.AllTotal();
        return BlogJSONResult.ok(p1);
    }

}
