package com.ksmarter.pointmarket.domain.assignment.service;

import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.account.repository.AccountRepository;
import com.ksmarter.pointmarket.domain.assignment.domain.Assignment;
import com.ksmarter.pointmarket.domain.assignment.domain.AssignmentSubmit;
import com.ksmarter.pointmarket.domain.assignment.repository.AssignmentRepository;
import com.ksmarter.pointmarket.domain.assignment.repository.AssignmentSubmitRepository;
import com.ksmarter.pointmarket.domain.common.enums.AssignmentSubmitTypes;
import com.ksmarter.pointmarket.domain.institute.domain.Institute;
import com.ksmarter.pointmarket.domain.institute.repository.InstituteRepository;
import com.ksmarter.pointmarket.generated.types.InputAssignment;
import com.ksmarter.pointmarket.generated.types.InputAssignmentSubmit;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AssignmentService {

    private final AccountRepository accountRepository;
    private final InstituteRepository instituteRepository;
    private final AssignmentRepository assignmentRepository;
    private final AssignmentSubmitRepository assignmentSubmitRepository;

    public AssignmentService(AccountRepository accountRepository, InstituteRepository instituteRepository, AssignmentRepository assignmentRepository, AssignmentSubmitRepository assignmentSubmitRepository) {
        this.accountRepository = accountRepository;
        this.instituteRepository = instituteRepository;
        this.assignmentRepository = assignmentRepository;
        this.assignmentSubmitRepository = assignmentSubmitRepository;
    }


    public Assignment saveByInputAssignment(InputAssignment inputAssignment) {

        Assignment.AssignmentBuilder assignmentBuilder = Assignment.builder();

        Institute institute = instituteRepository.findById(Long.parseLong(inputAssignment.getInstitute().getId())).orElseThrow();

        assignmentBuilder.institute(institute);
        assignmentBuilder.title(inputAssignment.getTitle());
        assignmentBuilder.startDate(inputAssignment.getStartDate());
        assignmentBuilder.endDate(inputAssignment.getEndDate());
        assignmentBuilder.rewardCredit(BigDecimal.valueOf(inputAssignment.getRewardCredit()));

        return assignmentRepository.save(assignmentBuilder.build());
    }

    public AssignmentSubmit saveByInputAssignmentSubmit(InputAssignmentSubmit inputAssignmentSubmit) {

        AssignmentSubmit.AssignmentSubmitBuilder assignmentSubmitBuilder = AssignmentSubmit.builder();

        Account account = accountRepository.findById(Long.parseLong(inputAssignmentSubmit.getAccount().getId())).orElseThrow();
        Assignment assignment = assignmentRepository.findById(Long.parseLong(inputAssignmentSubmit.getAssignment().getId())).orElseThrow();

        assignmentSubmitBuilder.assignment(assignment);
        assignmentSubmitBuilder.account(account);
        assignmentSubmitBuilder.comment(inputAssignmentSubmit.getComment());
        assignmentSubmitBuilder.assignmentSubmitType(AssignmentSubmitTypes.STANDBY);

        return assignmentSubmitRepository.save(assignmentSubmitBuilder.build());
    }
}
