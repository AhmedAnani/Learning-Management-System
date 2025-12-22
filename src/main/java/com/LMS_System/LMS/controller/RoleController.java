package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.DTO.AddRoleDto;
import com.LMS_System.LMS.service.RoleService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@NoArgsConstructor
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<Map<String,String>> addRole(@RequestBody AddRoleDto addRoleDto){
        try {
            return roleService.saveRole(addRoleDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<Map<String,?>> getAllRoles(){
        try {
            return roleService.getAllRoles();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
