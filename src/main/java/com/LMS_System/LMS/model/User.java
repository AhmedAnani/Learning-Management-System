package com.LMS_System.LMS.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String firstName;


    private String secondName;


    private LocalDate birthDate;


    private String email;

    private String phone;

    private String otp;

    private LocalDateTime otpExpiration;

    private boolean verified;

    private String password;

    @ManyToMany
    @JoinTable(
            name="users_roles",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
   private Set<Roles> roles;

    @ManyToMany
    @JoinTable(
            name = "users_courses",
            joinColumns =@JoinColumn(name = "users_id"),
            inverseJoinColumns=@JoinColumn(name = "courses_id")
    )
    private Set<Course> courses;


}
