package com.LMS_System.LMS.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizResponseDto {

    private int id;
    private String name;
    private List<String> questions;
    private LocalDate creationTime;
}
