package com.LMS_System.LMS.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddSectionDto {

    private String name;

    private  String description;

    private int courseId;
}
