package com.ksmarter.pointmarket.domain.auth.gql.fetcher;

import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.auth.service.UserService;
import com.ksmarter.pointmarket.generated.DgsConstants;
import com.ksmarter.pointmarket.generated.types.InputJoinAccount;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;
import jakarta.annotation.security.PermitAll;

@DgsComponent
public class UserMutationFetcher {
    private final UserService userService;

    public UserMutationFetcher(UserService userService) {
        this.userService = userService;
    }


    @PermitAll
    @DgsData(parentType = DgsConstants.MUTATIONRESOLVER.TYPE_NAME, field = DgsConstants.MUTATIONRESOLVER.Join)
    public Account join(DgsDataFetchingEnvironment dfe, @InputArgument InputJoinAccount inputJoinAccount) {

        Account account = userService.joinByInputJoinAccount(inputJoinAccount);

        return account;
    }
}
