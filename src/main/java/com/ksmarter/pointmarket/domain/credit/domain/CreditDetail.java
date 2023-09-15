package com.ksmarter.pointmarket.domain.credit.domain;

import com.ksmarter.pointmarket.domain.assignment.domain.AssignmentSubmit;
import com.ksmarter.pointmarket.domain.budget.domain.Budget;
import com.ksmarter.pointmarket.domain.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

@Entity
@Comment("사용자 포인트 내역 정보")
@Table(name = "credit_detail")
@Getter
@NoArgsConstructor
public class CreditDetail extends BaseEntity {

    @Id
    @Comment("사용자 포인트 내역 고유번호")
    @Column(name = "credit_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;

    @Comment("예산")
    @ManyToOne
    @JoinColumn(name = "budget_id", nullable = true)
    private Budget budget;

    @Comment("과제 수행")
    @OneToOne
    @JoinColumn(name = "assignment_submit_id", nullable = true)
    private AssignmentSubmit assignmentSubmit;

    @Comment("코멘트")
    @Column(name = "comment", length = 200)
    private String comment;

    @Column(name = "in_balance")
    @Comment("입금")
    private BigDecimal inBalance = BigDecimal.ZERO;

    @Column(name = "out_balance")
    @Comment("출금")
    private BigDecimal outBalance = BigDecimal.ZERO;

    @Column(name = "before_balance")
    @Comment("이전 잔액")
    private BigDecimal beforeBalance = BigDecimal.ZERO;

    @Column(name = "after_balance")
    @Comment("최종 잔액")
    private BigDecimal afterBalance = BigDecimal.ZERO;

    @PreUpdate
    private void preUpdate() {
        throw new UnsupportedOperationException();
    }
}