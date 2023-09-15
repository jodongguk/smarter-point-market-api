package com.ksmarter.pointmarket.domain.auth.gql.fetcher;

import com.ksmarter.pointmarket.domain.account.adapter.AccountAdapter;
import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.account.service.AccountJoinService;
import com.ksmarter.pointmarket.domain.auth.dto.ResponseLogin;
import com.ksmarter.pointmarket.generated.DgsConstants;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;
import jakarta.annotation.security.PermitAll;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Slf4j
@DgsComponent
public class UserQueryFetcher {
    private final AccountJoinService accountJoinService;

    public UserQueryFetcher(AccountJoinService accountJoinService) {
        this.accountJoinService = accountJoinService;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @DgsData(parentType = DgsConstants.QUERYRESOLVER.TYPE_NAME, field = DgsConstants.QUERYRESOLVER.UserMe)
    public Account userMe(DgsDataFetchingEnvironment dfe) throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return accountJoinService.getUserByUserId(userDetails.getUsername());
    }
}
