package com.LMS_System.LMS.model;


import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
public class BaseEntity {

    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt ;

    @Column(name = "created_by",updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

}