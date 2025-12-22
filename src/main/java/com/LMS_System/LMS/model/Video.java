package com.LMS_System.LMS.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "videos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Video extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String path;

    @Column(name = "creation_time",nullable = false)
    private LocalDate creationTime;

    @ManyToOne
    @JoinColumn(name = "content_id",nullable = false)
    private Content content;


}