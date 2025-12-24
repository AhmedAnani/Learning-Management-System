package com.LMS_System.LMS.dto.auth;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    private String first_name;

    private String second_name;

    private LocalDate birth_date;

    private String email;

    private String phone;

    private String password;

    
}
