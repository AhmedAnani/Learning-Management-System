package com.LMS_System.LMS.repository;

import com.LMS_System.LMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    User findById(int id);
}
