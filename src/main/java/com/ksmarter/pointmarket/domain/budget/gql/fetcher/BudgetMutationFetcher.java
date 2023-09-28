package com.ksmarter.pointmarket.domain.budget.gql.fetcher;

import com.ksmarter.pointmarket.domain.budget.domain.Budget;
import com.ksmarter.pointmarket.domain.budget.service.BudgetService;
import com.ksmarter.pointmarket.generated.DgsConstants;
import com.ksmarter.pointmarket.generated.types.InputBudget;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.security.access.prepost.PreAuthorize;

@DgsComponent
public class BudgetMutationFetcher {

    private final BudgetService budgetService;

    public BudgetMutationFetcher(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PreAuthorize("hasAnyRole('ROLE_INSTITUTE')")
    @DgsData(parentType = DgsConstants.MUTATIONRESOLVER.TYPE_NAME, field = DgsConstants.MUTATIONRESOLVER.Budget)
    public Budget budget(DgsDataFetchingEnvironment dfe, @InputArgument InputBudget inputBudget) {

        return budgetService.saveByInputBudget(inputBudget);
    }
}
