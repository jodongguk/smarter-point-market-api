package com.ksmarter.pointmarket.domain.budget.domain;

import com.ksmarter.pointmarket.domain.common.domain.BaseEntity;
import com.ksmarter.pointmarket.domain.institute.domain.Institute;
import com.ksmarter.pointmarket.generated.types.BudgetFilter;
import com.ksmarter.pointmarket.generated.types.InstituteFilter;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Predicate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "budget")
@Comment("예산 정보")
@Getter
@NoArgsConstructor
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


    @Builder
    public Budget(Long id, Institute institute, LocalDate startDate, LocalDate endDate, BigDecimal budgetAmount) {
        this.id = id;
        this.institute = institute;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budgetAmount = budgetAmount;
    }


    public static Specification<Budget> inputFilterToSpec(BudgetFilter filter) {
        return (root, query, builder) -> {
            Predicate p = builder.conjunction();

            if(filter.getInstitute() != null) {
                if(StringUtils.isNotEmpty(filter.getInstitute().getId())) {
                    p = builder.and(p, builder.equal(root.get("institute").get("id"), filter.getInstitute().getId()));
                }
            }

            return p;
        };
    }
}