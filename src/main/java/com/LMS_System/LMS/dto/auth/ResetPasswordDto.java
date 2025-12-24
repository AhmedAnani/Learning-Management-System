package com.LMS_System.LMS.dto.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordDto {


    private String email;

    private String otp;

    private String password;
}
