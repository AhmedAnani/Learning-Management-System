package com.LMS_System.LMS.service;

import com.LMS_System.LMS.dto.article.AddArticleDto;
import com.LMS_System.LMS.dto.article.GetArticleDto;
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

    public ResponseEntity<Map<String,String>> addArticle(AddArticleDto addArticleDto){
        Content content=contentRepository.findById(addArticleDto.getContentId()).orElse(null);

        if(content==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message","Content not found."));
        }
        Article article=new Article();
        article.setDescription(addArticleDto.getDescription());
        article.setName(addArticleDto.getName());
        article.setCreationTime(addArticleDto.getCreationTime());
        article.setContent(content);
        articleRepository.save(article);

        return  ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message","Added successfully."));
    }

    public List<?> getArticle(GetArticleDto getArticleDto){

        Article article=articleRepository.findById(getArticleDto.getArticleId()).orElse(null);
        if(article==null) {
            return List.of(ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message","Content not found.")));
        }

        return List.of(ResponseEntity.status(HttpStatus.OK)
                .body(article));
    }

}




























