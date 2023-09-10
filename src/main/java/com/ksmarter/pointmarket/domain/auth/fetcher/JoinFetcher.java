package com.ksmarter.pointmarket.domain.auth.fetcher;

import com.ksmarter.pointmarket.domain.account.service.AccountJoinService;
import com.netflix.graphql.dgs.DgsComponent;

@DgsComponent
public class JoinFetcher {
    private final AccountJoinService accountJoinService;

    public JoinFetcher(AccountJoinService accountJoinService) {
        this.accountJoinService = accountJoinService;
    }
}
