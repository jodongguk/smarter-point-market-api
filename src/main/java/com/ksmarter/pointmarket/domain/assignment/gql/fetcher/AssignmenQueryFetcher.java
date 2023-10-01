package com.ksmarter.pointmarket.domain.assignment.gql.fetcher;


import com.ksmarter.pointmarket.domain.assignment.domain.Assignment;
import com.ksmarter.pointmarket.domain.assignment.domain.AssignmentSubmit;
import com.ksmarter.pointmarket.domain.assignment.repository.AssignmentRepository;
import com.ksmarter.pointmarket.domain.assignment.repository.AssignmentSubmitRepository;
import com.ksmarter.pointmarket.generated.DgsConstants;
import com.ksmarter.pointmarket.generated.types.AssignmentFilter;
import com.ksmarter.pointmarket.generated.types.AssignmentSubmitFilter;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import graphql.relay.Connection;
import graphql.relay.SimpleListConnection;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@DgsComponent
public class AssignmenQueryFetcher {

    private final AssignmentRepository assignmentRepository;
    private final AssignmentSubmitRepository assignmentSubmitRepository;

    public AssignmenQueryFetcher(AssignmentRepository questRepository, AssignmentSubmitRepository assignmentSubmitRepository) {
        this.assignmentRepository = questRepository;
        this.assignmentSubmitRepository = assignmentSubmitRepository;
    }

    /**
     * 포인트마켓 > 일반유저 > 과제 목록
     *
     * @param dfe
     * @param filter
     * @param first
     * @param after
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @DgsData(parentType = DgsConstants.QUERYRESOLVER.TYPE_NAME)
    public Connection<Assignment> assignments(DataFetchingEnvironment dfe,
                                              @InputArgument AssignmentFilter filter,
                                              @InputArgument Integer first,
                                              @InputArgument String after) {


        List<Assignment> quests = assignmentRepository.findAll(Assignment.inputFilterToSpec(filter));
        return new SimpleListConnection<>(quests).get(dfe);
    }

    /**
     * 포인트마켓 > 일반유저 > 과제 제출 목록
     *
     * @param dfe
     * @param filter
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @DgsData(parentType = DgsConstants.QUERYRESOLVER.TYPE_NAME)
    public List<AssignmentSubmit> assignmentSubmits(DataFetchingEnvironment dfe,
                                                    @InputArgument AssignmentSubmitFilter filter) {
        List<AssignmentSubmit> assignmentSubmits = assignmentSubmitRepository.findAll(AssignmentSubmit.inputFilterToSpec(filter));
        return assignmentSubmits;
    }

}
