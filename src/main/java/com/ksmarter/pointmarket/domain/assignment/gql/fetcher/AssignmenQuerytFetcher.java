package com.ksmarter.pointmarket.domain.assignment.gql.fetcher;


import com.ksmarter.pointmarket.domain.assignment.domain.Assignment;
import com.ksmarter.pointmarket.domain.assignment.repository.AssignmentRepository;
import com.ksmarter.pointmarket.generated.DgsConstants;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import graphql.relay.Connection;
import graphql.relay.SimpleListConnection;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@DgsComponent
public class AssignmenQuerytFetcher {

    private final AssignmentRepository assignmentRepository;

    public AssignmenQuerytFetcher(AssignmentRepository questRepository) {
        this.assignmentRepository = questRepository;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @DgsData(parentType = DgsConstants.QUERYRESOLVER.TYPE_NAME)
    public Connection<Assignment> assignments(DataFetchingEnvironment dfe,
                                              @InputArgument Integer first,
                                              @InputArgument String after) {

        List<Assignment> quests = assignmentRepository.findAll();
        return new SimpleListConnection<>(quests).get(dfe);
    }

}
