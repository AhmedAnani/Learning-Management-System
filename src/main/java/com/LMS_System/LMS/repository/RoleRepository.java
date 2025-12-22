package com.LMS_System.LMS.repository;

import com.LMS_System.LMS.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    boolean existsByRole(String role);
}
