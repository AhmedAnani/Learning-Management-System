package com.LMS_System.LMS.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course extends Auditable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String author;

    @Column(name = "creation_time",nullable = false)
    private LocalDate creationTime;

    @Column(nullable = false)
    private double Rate;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int watches;

    @ManyToMany(mappedBy = "courses")
    private Set<User> users;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Section> sections;
}
