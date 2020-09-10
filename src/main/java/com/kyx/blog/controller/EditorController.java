package com.kyx.blog.controller;

import com.kyx.blog.entity.Blog;
import com.kyx.blog.entity.vo.BlogMessageVoEntity;
import com.kyx.blog.service.BlogService;
import com.kyx.blog.service.LabelService;
import com.kyx.blog.service.RedisService;
import com.kyx.util.BlogJSONResult;
import com.kyx.util.BulidArticleUtil;
import com.kyx.util.CommonUtils;
import com.kyx.util.StringAndArray;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EditorController {
    @Value("${kyx.file-path}")
    String path;
    @Autowired
    BlogService blogService;
    @Autowired
    LabelService labelService;
    /**
     * 发布博客
     * @param blogMessageVoEntity
     * @return
     */
    @PostMapping("/publishEditor")
    public BlogJSONResult publishEditor(@RequestBody BlogMessageVoEntity blogMessageVoEntity){
        // 生成文章摘要

        BulidArticleUtil buildArticleTabloidUtil = new BulidArticleUtil();
        String articleHtmlContent = buildArticleTabloidUtil.bulidArticle(blogMessageVoEntity.getArticleHtmlContent());
        blogMessageVoEntity.setArticleTabled(articleHtmlContent + "...");

        //标签
        String[] tagValue = blogMessageVoEntity.getTagValues();
        for (int i =0; i < tagValue.length; i++){
            // 去掉可能出现的换行符
            tagValue[i] =tagValue[i].replaceAll("<br>","").replaceAll("&nbsp","").replaceAll("\\s","").trim();
        }
        labelService.insLabel(tagValue);
        String label = StringAndArray.arrayToString(tagValue);
        blogMessageVoEntity.setLabelValues(label);
        if (blogMessageVoEntity.getSelectType()=="转载"){
            blogMessageVoEntity.setOriginalAuthor(blogMessageVoEntity.getOriginalAuthor());
        }else {blogMessageVoEntity.setOriginalAuthor("");}

        if(blogMessageVoEntity.getId()!=null){
            return BlogJSONResult.build(201,"",blogService.updBlogByBlogId(blogMessageVoEntity));
        }

        blogService.publishBlog(blogMessageVoEntity);
        return BlogJSONResult.ok(blogMessageVoEntity);
    }

    /**
     * 编辑博客
     * @param request
     * @return
     */
    @GetMapping("getDraftArticle")
    public BlogJSONResult getDraftArticle(HttpServletRequest request){
        String id =(String)request.getSession().getAttribute("id");
        //是否修改文章
        if(id!=null){
            BlogMessageVoEntity messageVoEntity=blogService.EchoBlogByBlogId(Long.parseLong(id));
            return BlogJSONResult.build(201,"",messageVoEntity);
        }
        return BlogJSONResult.ok();
    }
    @RequestMapping("/uploadImage")
    public Map<String,Object> uploadImage(HttpServletRequest request, @RequestParam(value = "editormd-image-file",required = true)MultipartFile file){
        Map<String,Object> map =new HashMap<>();
        String OriginalFilename =file.getOriginalFilename();
        String suffixName=OriginalFilename.substring(OriginalFilename.lastIndexOf("."));
        String filename =System.currentTimeMillis()+CommonUtils.getRandomNumber(100,999)+suffixName;
        String filepath = path +"/"+filename;

        try {
            //文件上传
            FileOutputStream fileOutputStream =null;
            File file1 =new File(filepath);
            if (file1.getParent()==null||!file1.isDirectory()){
                file1.getParentFile().mkdirs();
            }
            fileOutputStream =new FileOutputStream(file1);
            IOUtils.copy(file.getInputStream(),fileOutputStream);

            map.put("url","/upload/"+filename);
            map.put("success",1);
            map.put("message","upload success!");
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
}
