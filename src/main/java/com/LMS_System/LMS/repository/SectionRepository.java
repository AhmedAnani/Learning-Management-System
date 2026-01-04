package com.LMS_System.LMS.repository;

import com.LMS_System.LMS.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface SectionRepository extends JpaRepository<Section,Integer> {
    Set<Section> findByCourseId(int id);
}
