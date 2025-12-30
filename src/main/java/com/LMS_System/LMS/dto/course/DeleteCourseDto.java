package com.LMS_System.LMS.dto.course;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCourseDto {
    @NotBlank
    String courseName;

    @NotBlank
    String authorName;
}
