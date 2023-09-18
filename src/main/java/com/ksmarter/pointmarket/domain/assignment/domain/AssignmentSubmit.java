package com.ksmarter.pointmarket.domain.assignment.domain;

import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.common.domain.BaseEntity;
import com.ksmarter.pointmarket.domain.common.enums.AssignmentSubmitTypes;
import com.ksmarter.pointmarket.domain.common.enums.converter.AssignmentSubmitTypesConverter;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "assignment_submit")
@Comment("과제 제출")
@Getter
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
}