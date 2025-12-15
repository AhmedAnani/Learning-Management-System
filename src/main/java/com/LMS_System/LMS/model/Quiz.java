package com.LMS_System.LMS.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "quizzes")
public class Quiz extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private List<String> questions;

    private LocalDate creation_time;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content contents;

}
