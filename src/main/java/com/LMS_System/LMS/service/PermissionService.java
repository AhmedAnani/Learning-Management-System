package com.LMS_System.LMS.service;

import com.LMS_System.LMS.DTO.AddPermissionDto;
import com.LMS_System.LMS.model.Permission;
import com.LMS_System.LMS.model.Role;
import com.LMS_System.LMS.repository.PremissionRepository;
import com.LMS_System.LMS.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service

public class PermissionService {

    @Autowired
    private PremissionRepository premissionRepository;

    @Autowired private RoleRepository roleRepository;

    // 1. Add permission
    public ResponseEntity<Map<String,String>> addPermission(AddPermissionDto addPermissionDto){
       Permission permission=new Permission();
       Role role=roleRepository.findById(addPermissionDto.getRole()).orElseThrow(()->new RuntimeException("Role not found"));

       permission.setPermission(addPermissionDto.getPermission());
       permission.setRole(role);
        premissionRepository.save(permission);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message","Permission saved successfully to the role."));
    }

    // 2. Get all permissions
    public List<?> getAllPermission(){

        if (premissionRepository.findAll().isEmpty()){
            return List.of("There's no permission yet.");
        }
        return premissionRepository.findAll();
    }
}
