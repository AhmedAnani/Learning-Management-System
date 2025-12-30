package com.LMS_System.LMS.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class GetUserResponseDto {

    private String firstName;

    private String secondName;

    private  String email;

    private String phone;

    private LocalDate birthDate;
}
