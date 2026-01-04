package com.LMS_System.LMS.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerifyOtpDto {

    @NotBlank
    @Email
   private String email;

    @NotBlank
    @Size(min = 6)
   private String otp;
}
