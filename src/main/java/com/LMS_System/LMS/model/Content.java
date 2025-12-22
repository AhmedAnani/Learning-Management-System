package com.LMS_System.LMS.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "contents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Content extends Auditable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
    private Set<Video> videos;

    @OneToMany(mappedBy = "content",cascade = CascadeType.ALL)
    private Set<Article> articles;

    @OneToMany(mappedBy = "content",cascade = CascadeType.ALL)
    private Set<Quiz> quizzes;
}
