package com.LMS_System.LMS.dto.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCourseResponseDto {
    private String name;

    private String author;

    private String description;

    private LocalDate creation_time;

    private double price;

    private double Rate;

    private int watches;
}
