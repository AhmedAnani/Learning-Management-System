package com.LMS_System.LMS.repository;

import com.LMS_System.LMS.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section,Integer> {
    List<Section> findByCourseId(int id);
}
