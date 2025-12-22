package com.LMS_System.LMS.service;

import com.LMS_System.LMS.DTO.AddPermission;
import com.LMS_System.LMS.model.Permission;
import com.LMS_System.LMS.repository.PremissionRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@NoArgsConstructor
public class PremissionService {

    @Autowired
    private PremissionRepository premissionRepository;

    public ResponseEntity<Map<String,String>> addPermission(AddPermission addPermissionpermission){
       Permission permission=new Permission();
       permission.setPermission(addPermissionpermission.getPermission());
       permission.setRole(addPermissionpermission.getRole());
        premissionRepository.save(permission);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message","Permission saved successfully to the role."));
    }
}
