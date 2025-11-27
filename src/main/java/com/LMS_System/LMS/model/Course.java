package com.LMS_System.LMS.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "courses")
@Data
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String name;


    private String description;


    private String author;


    private LocalDate creation_time;


    private double Rate;


    private double price;


    private int watches;

    @ManyToMany(mappedBy = "courses")
    private Set<User> users;

    @OneToMany(mappedBy = "courses",cascade = CascadeType.ALL)
    private Set<Section> sections ;
}
