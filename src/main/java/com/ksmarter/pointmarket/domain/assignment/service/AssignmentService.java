package com.ksmarter.pointmarket.domain.assignment.service;

import com.ksmarter.pointmarket.domain.assignment.domain.Assignment;
import com.ksmarter.pointmarket.domain.assignment.repository.AssignmentRepository;
import com.ksmarter.pointmarket.domain.budget.domain.Budget;
import com.ksmarter.pointmarket.domain.institute.domain.Institute;
import com.ksmarter.pointmarket.domain.institute.repository.InstituteRepository;
import com.ksmarter.pointmarket.generated.types.InputAssignment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AssignmentService {

    private final InstituteRepository instituteRepository;
    private final AssignmentRepository assignmentRepository;

    public AssignmentService(InstituteRepository instituteRepository, AssignmentRepository assignmentRepository) {
        this.instituteRepository = instituteRepository;
        this.assignmentRepository = assignmentRepository;
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
}
