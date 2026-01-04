package com.LMS_System.LMS.repository;

import com.LMS_System.LMS.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Integer> {
}
