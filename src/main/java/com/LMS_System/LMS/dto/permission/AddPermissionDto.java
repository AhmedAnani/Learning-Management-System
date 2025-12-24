package com.LMS_System.LMS.dto.permission;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddPermissionDto {

    private String permission;

    private int role;

}
