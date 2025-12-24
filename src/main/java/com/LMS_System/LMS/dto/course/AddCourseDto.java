package com.LMS_System.LMS.dto.course;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCourseDto {

    private String name;


    private String description;

    private LocalDate creation_time;

    private double price;
}
