package com.LMS_System.LMS.dto.role;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddRoleDto {

    @NotBlank
    private String role;
}
