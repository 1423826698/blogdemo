package com.kyx.blog.controller;

import com.kyx.blog.entity.vo.BlogMessageVoEntity;
import com.kyx.blog.service.ArticleService;
import com.kyx.blog.service.BlogService;
import com.kyx.util.BlogJSONResult;
import com.kyx.util.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    BlogService Blogservice;
    @GetMapping("/myArticle")
    public BlogJSONResult myArticle(@RequestParam("page") int page,@RequestParam("pageSize") int pageSize){

        PagedResult result=articleService.findAllBlog(page,pageSize);
       // System.out.println("ok");
        return BlogJSONResult.ok(result);
    }

    @GetMapping("getArticleDetail")
    public BlogJSONResult getArticleDetail(@RequestParam("articleId") long articleId){
        BlogMessageVoEntity Id = Blogservice.findBlogById(articleId);
        return BlogJSONResult.ok(Id);
    }
    @GetMapping("getTagDetail")
    public BlogJSONResult getTagDetail(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize,@RequestParam("tag")String tag){
        System.out.println("TAGS ===="+tag);
        return BlogJSONResult.ok( articleService.findByTag(pageNum,pageSize,tag));
    }
}
