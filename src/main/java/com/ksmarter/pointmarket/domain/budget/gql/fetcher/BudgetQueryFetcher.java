package com.ksmarter.pointmarket.domain.budget.gql.fetcher;


import com.ksmarter.pointmarket.domain.budget.domain.Budget;
import com.ksmarter.pointmarket.domain.budget.repository.BudgetRepository;
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
public class BudgetQueryFetcher {

    private final BudgetRepository budgetRepository;

    public BudgetQueryFetcher(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @PreAuthorize("hasAnyRole('ROLE_INSTITUTE')")
    @DgsData(parentType = DgsConstants.QUERYRESOLVER.TYPE_NAME)
    public Connection<Budget> budgets(DataFetchingEnvironment dfe,
                                    @InputArgument Integer first,
                                    @InputArgument String after) {

        List<Budget> badges = budgetRepository.findAll();
        return new SimpleListConnection<>(badges).get(dfe);
    }

}