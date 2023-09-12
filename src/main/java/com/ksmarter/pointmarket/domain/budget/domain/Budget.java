package com.ksmarter.pointmarket.domain.budget.domain;

import com.ksmarter.pointmarket.domain.common.domain.BaseEntity;
import com.ksmarter.pointmarket.domain.institute.domain.Institute;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "budget")
@Comment("예산 정보")
@Getter
public class Budget extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_id", nullable = false)
    private Long id;

    @Comment("예산 등록 학원")
    @ManyToOne
    @JoinColumn(name = "institute_id", nullable = false)
    private Institute institute;

    @Comment("예산 시작일")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Comment("예산 종료일")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Comment("예산 금액")
    @Column(name = "budget_amount", nullable = false)
    private BigDecimal budgetAmount = BigDecimal.ZERO;
    
}