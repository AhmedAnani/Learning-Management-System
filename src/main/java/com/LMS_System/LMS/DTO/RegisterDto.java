package com.LMS_System.LMS.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterDto {

    private String first_name;

    private String second_name;

    private LocalDate birth_date;

    private String email;

    private String phone;

    private String password;

    
}
