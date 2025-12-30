package com.LMS_System.LMS.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponseDto {

    private String firstName;

    private String secondName;

    private  String email;

    private String phone;

    private LocalDate birthDate;
}
