package com.LMS_System.LMS.dto.video;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String name;

    @NotBlank
    private String path;

    @NotBlank
    private  LocalDate creationTime;

    @NotBlank
    private int contentId;
}
