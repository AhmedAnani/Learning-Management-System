package com.LMS_System.LMS.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "contents")
public class Content extends BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section sections;

    @OneToMany(mappedBy = "contents",cascade =  CascadeType.ALL)
    private Set<Video> videos;

    @OneToMany(mappedBy = "contents",cascade = CascadeType.ALL)
    private Set<Article> articles;

    @OneToMany(mappedBy = "contents",cascade = CascadeType.ALL)
    private Set<Quiz> quizzes;
}
