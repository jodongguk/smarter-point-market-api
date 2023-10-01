package com.ksmarter.pointmarket.domain.auth.gql.fetcher;

import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.account.repository.AccountRepository;
import com.ksmarter.pointmarket.domain.account.service.AccountJoinService;
import com.ksmarter.pointmarket.generated.DgsConstants;
import com.ksmarter.pointmarket.generated.types.AccountFilter;
import com.ksmarter.pointmarket.generated.types.InputJoinAccount;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;
import jakarta.annotation.security.PermitAll;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Slf4j
@DgsComponent
public class UserQueryFetcher {
    private final AccountRepository accountRepository;
    private final AccountJoinService accountJoinService;

    public UserQueryFetcher(AccountRepository accountRepository, AccountJoinService accountJoinService) {
        this.accountRepository = accountRepository;
        this.accountJoinService = accountJoinService;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @DgsData(parentType = DgsConstants.QUERYRESOLVER.TYPE_NAME, field = DgsConstants.QUERYRESOLVER.UserMe)
    public Account userMe(DgsDataFetchingEnvironment dfe) throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return accountJoinService.getUserByUserId(userDetails.getUsername());
    }

    @PermitAll
    @DgsData(parentType = DgsConstants.QUERYRESOLVER.TYPE_NAME, field = DgsConstants.QUERYRESOLVER.Chidren)
    public List<Account> children(DgsDataFetchingEnvironment dfe,
                                  @InputArgument AccountFilter filter) throws Exception {

        return accountRepository.findAll(Account.inputFilterToSpec(filter));
    }
}
