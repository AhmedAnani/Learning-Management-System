package com.LMS_System.LMS.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "sections")
public class Section extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course courses;

    @OneToMany(mappedBy = "sections",cascade = CascadeType.ALL)
    private Set<Content> contents;


}
