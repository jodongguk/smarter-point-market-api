package com.ksmarter.pointmarket.domain.assignment.domain;

import com.ksmarter.pointmarket.domain.common.domain.BaseEntity;
import com.ksmarter.pointmarket.domain.institute.domain.Institute;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "assignment")
@Comment("과제 정보")
@Getter
public class Assignment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id", nullable = false)
    private Long id;

    @Comment("과제 등록 학원")
    @ManyToOne
    @JoinColumn(name = "institute_id", nullable = false)
    private Institute institute;

    @Comment("과제 시작일")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Comment("과제 종료일")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Comment("과제 수행 금액")
    @Column(name = "reward_credit", nullable = false)
    private BigDecimal rewardCredit = BigDecimal.ZERO;

    @OneToMany(mappedBy = "assignment")
    private Set<AssignmentSubmit> assignmentSubmits;
    
}