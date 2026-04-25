package com.pooranjoyb.order_stream.core.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class AuditFields {
    private LocalDateTime createdAt;
    private  String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    @PrePersist
    protected void preInsert() {
        setCreatedBy("API");
        setUpdatedBy("API");
        setCreatedAt(LocalDateTime.now());
        setUpdatedAt(LocalDateTime.now());
    }

    @PreUpdate
    protected void preUpdate() {
        setUpdatedAt(LocalDateTime.now());
    }
}
