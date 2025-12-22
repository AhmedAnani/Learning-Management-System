package com.LMS_System.LMS.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "quizzes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quiz extends Auditable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private List<String> questions;

    @Column(name = "creation_time",nullable = false)
    private LocalDate creationTime;

    @ManyToOne
    @JoinColumn(name = "content_id",nullable = false)
    private Content content;

}
