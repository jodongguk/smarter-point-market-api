package com.ksmarter.pointmarket.domain.account.gql.fetcher;

import com.ksmarter.pointmarket.constants.DgsTypeConst;
import com.ksmarter.pointmarket.domain.account.gql.context.AccountContextBuilder;
import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.account.repository.AccountRepository;
import com.ksmarter.pointmarket.generated.DgsConstants;
import com.ksmarter.pointmarket.generated.types.AccountFilter;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import graphql.relay.Connection;
import graphql.relay.SimpleListConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Slf4j
@DgsComponent
public class AccountFetcher {

    private final AccountRepository accountRepository;
    private final AccountContextBuilder accountContextBuilder;

    public AccountFetcher(AccountRepository accountRepository, AccountContextBuilder accountContextBuilder) {
        this.accountRepository = accountRepository;
        this.accountContextBuilder = accountContextBuilder;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DgsData(parentType = DgsTypeConst.QueryResolver)
    public Connection<Account> accounts(DgsDataFetchingEnvironment dfe,
                                        @InputArgument AccountFilter filter,
                                        @InputArgument Integer first,
                                        @InputArgument String after) {

        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(grantedAuthority -> log.debug(grantedAuthority.getAuthority()));


        List<Account> accounts = accountRepository.findAll();
        accountContextBuilder.withAccounts(accounts).build();
        return new SimpleListConnection<>(accounts).get(dfe);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DgsData(parentType = DgsTypeConst.QueryResolver)
    public Account account(DgsDataFetchingEnvironment dfe,
                           @InputArgument("id") Long id) {
        return accountRepository.findById(id).orElseThrow(DgsEntityNotFoundException::new);
    }
}
