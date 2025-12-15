package com.LMS_System.LMS.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Columns;

import java.util.Set;

@Entity
@Data
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String role;

    @OneToMany(
            mappedBy = "role",
            cascade = CascadeType.ALL
    )
    private Set<Permission> permissions;
}

