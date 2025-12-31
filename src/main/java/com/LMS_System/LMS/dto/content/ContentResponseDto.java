package com.LMS_System.LMS.dto.content;

import com.LMS_System.LMS.dto.article.ArticleResponseDto;
import com.LMS_System.LMS.dto.quiz.QuizResponseDto;
import com.LMS_System.LMS.dto.video.VideoResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentResponseDto {

    private int id;

    private String name;

    private int sectionId;

    private Set<VideoResponseDto> videos;

    private Set<ArticleResponseDto> articles;

    private Set<QuizResponseDto> quizzes;

}
