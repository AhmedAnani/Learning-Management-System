package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.dto.ResponseDto;
import com.LMS_System.LMS.dto.article.AddArticleDto;
import com.LMS_System.LMS.dto.article.ArticleResponseDto;
import com.LMS_System.LMS.dto.article.GetArticleDto;
import com.LMS_System.LMS.service.ArticleService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ResponseDto> addArticle(@Valid @RequestBody AddArticleDto addArticleDto){

        return ResponseEntity.ok(articleService.addArticle(addArticleDto));
    }

    @GetMapping
    public ResponseEntity<ArticleResponseDto> getArticle(@Valid @RequestBody GetArticleDto getArticleDto){

        return ResponseEntity.ok(articleService.getArticle(getArticleDto));
    }

    @DeleteMapping
    public  ResponseEntity<ResponseDto> deleteArticle(@Valid @RequestBody GetArticleDto getArticleDto){

        return ResponseEntity.ok(articleService.deleteArticle(getArticleDto));
    }
}
