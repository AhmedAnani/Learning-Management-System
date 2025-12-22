package com.LMS_System.LMS.repository;

import com.LMS_System.LMS.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Integer> {
}
