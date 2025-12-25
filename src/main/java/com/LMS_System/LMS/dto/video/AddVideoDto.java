package com.LMS_System.LMS.dto.video;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddVideoDto {

    private String name;

    private String path;

    private  LocalDate creationTime;

    private int contentId;
}
