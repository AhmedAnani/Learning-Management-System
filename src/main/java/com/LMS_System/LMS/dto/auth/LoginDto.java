package com.LMS_System.LMS.dto.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    private String email;

    private  String Password;
}
