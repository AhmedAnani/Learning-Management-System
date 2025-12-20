package com.LMS_System.LMS.DTO;

import lombok.Data;

import java.time.LocalDate;
@Data
public class AddCourseDto {

    private String name;


    private String description;

    private String author;

    private LocalDate creation_time;

    private double price;
}
