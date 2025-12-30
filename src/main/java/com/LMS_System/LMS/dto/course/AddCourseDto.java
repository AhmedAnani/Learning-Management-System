package com.LMS_System.LMS.dto.course;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCourseDto {
    @NotBlank
    private String name;

    @NotBlank
    private String author;

    @NotBlank
    private String description;

    @NotBlank
    private LocalDate creation_time;

    @NotBlank
    private double price;
}
