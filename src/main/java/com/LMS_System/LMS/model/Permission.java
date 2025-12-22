package com.LMS_System.LMS.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "permissions")
public class Permission extends Auditable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String permission;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

}
