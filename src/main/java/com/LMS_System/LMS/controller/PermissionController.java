package com.LMS_System.LMS.controller;

import com.LMS_System.LMS.DTO.AddPermissionDto;
import com.LMS_System.LMS.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public List<?> getAllPermissions(){
        return permissionService.getAllPermission();
    }

    @PostMapping
    public ResponseEntity<Map<String,String>> addPermission(@RequestBody AddPermissionDto addPermissionDto){
        return permissionService.addPermission(addPermissionDto);
    }
}
