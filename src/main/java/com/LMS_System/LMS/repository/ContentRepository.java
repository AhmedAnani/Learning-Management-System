package com.LMS_System.LMS.repository;

import com.LMS_System.LMS.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content,Integer> {

    boolean existsById(int sectionId);

}
