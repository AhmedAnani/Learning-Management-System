package com.LMS_System.LMS.service;

import com.LMS_System.LMS.dto.ResponseDto;
import com.LMS_System.LMS.dto.permission.AddPermissionDto;
import com.LMS_System.LMS.dto.permission.GetPermissionDto;
import com.LMS_System.LMS.dto.permission.PermissionResponseDto;
import com.LMS_System.LMS.exception.NotFound;
import com.LMS_System.LMS.model.Permission;
import com.LMS_System.LMS.model.Role;
import com.LMS_System.LMS.repository.PermissionRepository;
import com.LMS_System.LMS.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired private RoleRepository roleRepository;

    // 1. Add permission
    public ResponseDto addPermission(AddPermissionDto addPermissionDto){

       Role role=roleRepository.findById(addPermissionDto.getRole()).orElseThrow(()->new NotFound("Role not found"));

        Permission permission=new Permission();
       permission.setPermission(addPermissionDto.getPermission());
       permission.setRole(role);
        permissionRepository.save(permission);
        return new ResponseDto("Permission saved successfully to the role.");
    }

    // 2. Get all permissions
    public List<PermissionResponseDto> getAllPermission(){

        List<Permission> permissions= permissionRepository.findAll();

        return permissions.stream().map(permission -> new PermissionResponseDto(permission.getId(),permission.getPermission(),
                permission.getRole().getId())).toList();
    }

    public PermissionResponseDto getPermission(GetPermissionDto getPermissionDto){
        Permission permission=permissionRepository.findById(getPermissionDto.getId()).orElseThrow(()->new NotFound("permission not found"));

        return new PermissionResponseDto(permission.getId()
                     ,permission.getPermission()
                     ,permission.getRole().getId());
    }
}
