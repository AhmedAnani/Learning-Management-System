package com.LMS_System.LMS.dto.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponseDto {

    private Long id;

    private String name;

    private String description;

    private LocalDate creationTime;

}
