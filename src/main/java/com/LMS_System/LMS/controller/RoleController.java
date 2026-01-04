package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.dto.ResponseDto;
import com.LMS_System.LMS.dto.role.AddRoleDto;
import com.LMS_System.LMS.dto.role.RoleResponseDto;
import com.LMS_System.LMS.service.RoleService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<ResponseDto> addRole(@Valid @RequestBody AddRoleDto addRoleDto){

            return ResponseEntity.ok(roleService.saveRole(addRoleDto));

    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDto>> getAllRoles(){

            return ResponseEntity.ok(roleService.getAllRoles());

    }
}
