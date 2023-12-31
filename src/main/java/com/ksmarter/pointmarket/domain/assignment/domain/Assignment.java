package com.ksmarter.pointmarket.domain.assignment.domain;

import com.ksmarter.pointmarket.domain.common.domain.BaseEntity;
import com.ksmarter.pointmarket.domain.institute.domain.Institute;
import com.ksmarter.pointmarket.generated.types.AssignmentFilter;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Predicate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "assignment")
@Comment("과제 정보")
@Getter
@NoArgsConstructor
public class Assignment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id", nullable = false)
    private Long id;

    @Comment("과제 등록 학원")
    @ManyToOne
    @JoinColumn(name = "institute_id", nullable = false)
    private Institute institute;

    @Comment("과제 제목")
    @Column(name = "title", nullable = false)
    private String title;

    @Comment("과제 시작일")
    @Column(name = "start_date", nullable = true)
    private LocalDate startDate;

    @Comment("과제 종료일")
    @Column(name = "end_date", nullable = true)
    private LocalDate endDate;

    @Comment("과제 수행 금액")
    @Column(name = "reward_credit", nullable = false)
    private BigDecimal rewardCredit = BigDecimal.ZERO;

    @OneToMany(mappedBy = "assignment")
    private Set<AssignmentSubmit> assignmentSubmits;


    @Builder
    public Assignment(Long id, Institute institute, String title, LocalDate startDate, LocalDate endDate, BigDecimal rewardCredit) {
        this.id = id;
        this.institute = institute;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rewardCredit = rewardCredit;
    }

    public static Specification<Assignment> inputFilterToSpec(AssignmentFilter filter) {
        return (root, query, builder) -> {
            Predicate p = builder.conjunction();

            if(filter.getInstitute() != null) {
                if(StringUtils.isNotEmpty(filter.getInstitute().getId())) {
                    p = builder.and(p, builder.equal(root.get("institute").get("id"), filter.getInstitute().getId()));
                }
            }
            if(StringUtils.isNotEmpty(filter.getChildren())) {
                p = builder.and(p, builder.equal(root.get("institute").get("children").get("children").get("id"), filter.getChildren()));
            }

            return p;
        };
    }
}