package com.LMS_System.LMS.service;

import com.LMS_System.LMS.dto.ResponseDto;
import com.LMS_System.LMS.dto.role.AddRoleDto;
import com.LMS_System.LMS.dto.role.RoleResponseDto;
import com.LMS_System.LMS.exception.NotAcceptable;
import com.LMS_System.LMS.model.Permission;
import com.LMS_System.LMS.model.Role;
import com.LMS_System.LMS.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public ResponseDto saveRole(AddRoleDto addRoleDto){
        if(addRoleDto.getRole().isBlank()){
            throw new NotAcceptable ("Can't be empty");
        }
        if(!roleRepository.existsByRole(addRoleDto.getRole())){
            throw new NotAcceptable ("Role already exist");
        }
        Role role =new Role();
        role.setRole(addRoleDto.getRole());
        roleRepository.save(role);
        return new ResponseDto("Role saved successfully");
    }

    public List<RoleResponseDto> getAllRoles() {
            List<Role> roles=roleRepository.findAll();
        return roles.stream().map(role -> new RoleResponseDto(role.getId(),
                role.getRole(),
                role.getPermissions().stream().map(Permission::getId).toList())).toList();
    }
}
