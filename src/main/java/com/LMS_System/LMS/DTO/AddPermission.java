package com.LMS_System.LMS.DTO;

import com.LMS_System.LMS.model.Role;
import lombok.Data;

@Data
public class AddPermission {

    private String permission;

    private Role role;

}
