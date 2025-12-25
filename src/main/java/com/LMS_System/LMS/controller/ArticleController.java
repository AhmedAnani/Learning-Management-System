package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.dto.article.AddArticleDto;
import com.LMS_System.LMS.dto.article.GetArticleDto;
import com.LMS_System.LMS.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseEntity<Map<String,String>> addArticle(@RequestBody AddArticleDto addArticleDto){
        return articleService.addArticle(addArticleDto);
    }

    @GetMapping
    public List<?> getArticle(@RequestBody GetArticleDto getArticleDto){
        return articleService.getArticle(getArticleDto);
    }
}
