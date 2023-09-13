package com.ksmarter.pointmarket.domain.common.domain;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", updatable = false)
    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    @Column
    private LocalDateTime updated_at;

    @Column(name = "updated_by")
    @LastModifiedBy
    private String updatedBy;
}
