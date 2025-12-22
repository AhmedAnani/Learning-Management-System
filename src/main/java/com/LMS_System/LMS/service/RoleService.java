package com.LMS_System.LMS.service;

import com.LMS_System.LMS.DTO.AddRoleDto;
import com.LMS_System.LMS.model.Role;
import com.LMS_System.LMS.repository.RoleRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public ResponseEntity<Map<String,String>> saveRole(AddRoleDto addRoleDto){
        if(addRoleDto.getRole().isBlank()){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(Map.of("message","Can't be empty"));
        }
        if(!roleRepository.existsByRole(addRoleDto.getRole())){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(Map.of("message","Role already exist"));
        }
        Role role =new Role();
        role.setRole(addRoleDto.getRole());
        roleRepository.save(role);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message","Role saved successfully"));
    }

    public ResponseEntity<Map<String,?>> getAllRoles() {
        if (roleRepository.findAll().isEmpty()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Map.of("message","There's no roles"));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("All roles",roleRepository.findAll()));
    }
}
