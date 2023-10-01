package com.ksmarter.pointmarket.domain.assignment.gql.fetcher;


import com.ksmarter.pointmarket.domain.assignment.domain.Assignment;
import com.ksmarter.pointmarket.domain.assignment.domain.AssignmentSubmit;
import com.ksmarter.pointmarket.domain.assignment.repository.AssignmentSubmitRepository;
import com.ksmarter.pointmarket.domain.assignment.service.AssignmentService;
import com.ksmarter.pointmarket.generated.DgsConstants;
import com.ksmarter.pointmarket.generated.types.InputAssignment;
import com.ksmarter.pointmarket.generated.types.InputAssignmentSubmit;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@DgsComponent
public class AssignmenMutationFetcher {

    private final AssignmentService assignmentService;

    public AssignmenMutationFetcher(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    /**
     * 포인트마켓 > 학원 > 과제 등록
     *
     * @param dfe
     * @param inputAssignment
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_INSTITUTE')")
    @DgsData(parentType = DgsConstants.MUTATIONRESOLVER.TYPE_NAME, field = DgsConstants.MUTATIONRESOLVER.Assignment)
    public Assignment assignments(DataFetchingEnvironment dfe,
                                  @InputArgument InputAssignment inputAssignment) {

        return assignmentService.saveByInputAssignment(inputAssignment);
    }

    /**
     * 포인트마켓 > 학부모 > 과제 제출
     *
     * @param dfe
     * @param inputAssignmentSubmit
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_PARENT')")
    @DgsData(parentType = DgsConstants.MUTATIONRESOLVER.TYPE_NAME, field = DgsConstants.MUTATIONRESOLVER.AssignmentSubmit)
    public AssignmentSubmit assignmentSubmit(DataFetchingEnvironment dfe,
                                             @InputArgument InputAssignmentSubmit inputAssignmentSubmit) {

        return assignmentService.saveByInputAssignmentSubmit(inputAssignmentSubmit);
    }

    @PreAuthorize("hasAnyRole('ROLE_INSTITUTE')")
    @DgsData(parentType = DgsConstants.MUTATIONRESOLVER.TYPE_NAME, field = DgsConstants.MUTATIONRESOLVER.AssignmentSubmitType)
    public List<AssignmentSubmit> assignmentSubmitType(DataFetchingEnvironment dfe,
                                                 @InputArgument List<InputAssignmentSubmit> list) {

        return assignmentService.saveByInputAssignmentSubmitType(list);
    }

}
