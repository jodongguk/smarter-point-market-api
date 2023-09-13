package com.ksmarter.pointmarket.domain.quest.domain;

import com.ksmarter.pointmarket.domain.common.domain.BaseEntity;
import com.ksmarter.pointmarket.domain.institute.domain.Institute;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "quest")
@Comment("미션 정보")
@Getter
public class Quest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quest_id", nullable = false)
    private Long id;

    @Comment("미션 등록 학원")
    @ManyToOne
    @JoinColumn(name = "institute_id", nullable = false)
    private Institute institute;

    @Comment("미션 시작일")
    @Column(name = "quest_start_date", nullable = false)
    private LocalDate questStartDate;

    @Comment("미션 종료일")
    @Column(name = "quest_end_date", nullable = false)
    private LocalDate questEndDate;

    @Comment("미션 수행 금액")
    @Column(name = "quest_reward_credit", nullable = false)
    private BigDecimal questRewardCredit = BigDecimal.ZERO;
    
}