package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.dto.ResponseDto;
import com.LMS_System.LMS.dto.permission.AddPermissionDto;
import com.LMS_System.LMS.dto.permission.GetPermissionDto;
import com.LMS_System.LMS.dto.permission.PermissionResponseDto;
import com.LMS_System.LMS.service.PermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/all")
    public ResponseEntity<List<PermissionResponseDto>> getAllPermissions(){

        return ResponseEntity.ok(permissionService.getAllPermission());
    }

    @PostMapping
    public ResponseEntity<ResponseDto> addPermission(@Valid @RequestBody AddPermissionDto addPermissionDto){

        return ResponseEntity.ok(permissionService.addPermission(addPermissionDto));
    }

    @GetMapping
    public ResponseEntity<PermissionResponseDto> getPermission(@Valid @RequestBody GetPermissionDto getPermissionDto){

        return ResponseEntity.ok(permissionService.getPermission(getPermissionDto));
    }
}
