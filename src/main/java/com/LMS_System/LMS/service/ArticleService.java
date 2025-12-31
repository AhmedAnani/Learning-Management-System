package com.LMS_System.LMS.service;

import com.LMS_System.LMS.dto.ResponseDto;
import com.LMS_System.LMS.dto.article.AddArticleDto;
import com.LMS_System.LMS.dto.article.ArticleResponseDto;
import com.LMS_System.LMS.dto.article.GetArticleDto;
import com.LMS_System.LMS.exception.NotFound;
import com.LMS_System.LMS.model.Article;
import com.LMS_System.LMS.model.Content;
import com.LMS_System.LMS.repository.ArticleRepository;
import com.LMS_System.LMS.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ContentRepository contentRepository;

    public ResponseDto addArticle(AddArticleDto addArticleDto){
        Content content=contentRepository.findById(addArticleDto.getContentId()).orElseThrow(()->new NotFound("Content not found"));

        Article article=new Article();
        article.setDescription(addArticleDto.getDescription());
        article.setName(addArticleDto.getName());
        article.setCreationTime(addArticleDto.getCreationTime());
        article.setContent(content);
        articleRepository.save(article);

        return  new ResponseDto("Added successfully.");
    }

    public ArticleResponseDto getArticle(GetArticleDto getArticleDto){

        Article article=articleRepository.findById(getArticleDto.getArticleId()).orElseThrow(()->new NotFound("Content not found"));

        return new ArticleResponseDto(  article.getId(),
                                        article.getName(),
                                        article.getDescription(),
                                        article.getCreationTime());
    }

    public ResponseDto deleteArticle(GetArticleDto getArticleDto){
        Article article=articleRepository.findById(getArticleDto.getArticleId()).orElseThrow(()->new NotFound("Content not found"));

        articleRepository.delete(article);
        return new ResponseDto("Article deleted successfully");
    }

}




























