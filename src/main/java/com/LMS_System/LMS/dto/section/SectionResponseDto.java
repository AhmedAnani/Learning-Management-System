package com.LMS_System.LMS.dto.section;

import com.LMS_System.LMS.dto.content.ContentBasicResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Setter
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class SectionResponseDto {

    private int id;

    private String name;

    private  String description;

    private int courseId;

    private Set<ContentBasicResponseDto> contents;
}
