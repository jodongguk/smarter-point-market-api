package com.ksmarter.pointmarket.domain.assignment.domain;

import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.common.domain.BaseEntity;
import com.ksmarter.pointmarket.domain.common.enums.AssignmentSubmitTypes;
import com.ksmarter.pointmarket.domain.common.enums.AuthorityRoles;
import com.ksmarter.pointmarket.domain.common.enums.converter.AssignmentSubmitTypesConverter;
import com.ksmarter.pointmarket.generated.types.AccountFilter;
import com.ksmarter.pointmarket.generated.types.AssignmentSubmitFilter;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Predicate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Comment;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;

@Entity
@Table(name = "assignment_submit")
@Comment("과제 제출")
@Getter
@NoArgsConstructor
public class AssignmentSubmit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_submit_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assignment_assignment_id")
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Comment("코멘트")
    @Column(name = "comment", nullable = false, length = 200)
    private String comment;

    @Comment("과제 제출 상태")
    @Column(name = "assignment_submit_type", nullable = false, length = 20)
    @Convert(converter = AssignmentSubmitTypesConverter.class)
    private AssignmentSubmitTypes assignmentSubmitType = AssignmentSubmitTypes.STANDBY;

    @Builder
    public AssignmentSubmit(Long id, Assignment assignment, Account account, String comment, AssignmentSubmitTypes assignmentSubmitType) {
        this.id = id;
        this.assignment = assignment;
        this.account = account;
        this.comment = comment;
        this.assignmentSubmitType = assignmentSubmitType;
    }

    public static Specification<AssignmentSubmit> inputFilterToSpec(AssignmentSubmitFilter filter) {
        return (root, query, builder) -> {
            Predicate p = builder.conjunction();

            if(StringUtils.isNotEmpty(filter.getAssignment().getId())) {
                p = builder.and(p, builder.equal(root.get("assignment").get("id"), filter.getAssignment().getId()));
            }

            return p;
        };
    }
}