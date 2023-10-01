package com.ksmarter.pointmarket.domain.common.domain;


import com.ksmarter.pointmarket.domain.account.domain.Account;
import jakarta.persistence.*;
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

    @JoinColumn(name = "created_by", updatable = false)
    @ManyToOne
    @CreatedBy
    private Account createdBy;

    @LastModifiedDate
    @Column
    private LocalDateTime updated_at;

    @JoinColumn(name = "updated_by")
    @ManyToOne
    @LastModifiedBy
    private Account updatedBy;
}
