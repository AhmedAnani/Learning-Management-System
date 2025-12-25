package com.LMS_System.LMS.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddQuizDto {

    private String name;

    private List<String> questions;

    private int contentId;

    private LocalDate creationTime;
}
