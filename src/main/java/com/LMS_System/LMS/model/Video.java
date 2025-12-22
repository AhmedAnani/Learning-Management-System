package com.LMS_System.LMS.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "videos")
public class Video extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String path;

    private LocalDate creationTime;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content contents;


}