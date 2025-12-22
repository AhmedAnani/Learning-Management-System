package com.LMS_System.LMS.DTO;

import com.LMS_System.LMS.model.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddPermissionDto {

    private String permission;

    private int role;

}
