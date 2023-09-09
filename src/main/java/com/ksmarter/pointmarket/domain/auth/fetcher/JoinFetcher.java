package com.ksmarter.pointmarket.domain.auth.fetcher;

import com.ksmarter.pointmarket.domain.account.service.AccountJoinService;
import com.ksmarter.pointmarket.generated.types.AccountInput;
import com.ksmarter.pointmarket.generated.types.AccountType;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;

@DgsComponent
public class JoinFetcher {
    private final AccountJoinService accountJoinService;

    public JoinFetcher(AccountJoinService accountJoinService) {
        this.accountJoinService = accountJoinService;
    }

   /* @DgsMutation
    public AccountType join(DataFetchingEnvironment dfe,
                        @InputArgument AccountInput accountInput) {


        return accountJoinService.join();
    }*/
}
