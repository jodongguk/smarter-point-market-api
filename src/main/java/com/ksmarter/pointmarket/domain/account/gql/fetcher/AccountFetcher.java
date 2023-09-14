package com.ksmarter.pointmarket.domain.account.gql.fetcher;

import com.ksmarter.pointmarket.constants.DgsTypeConst;
import com.ksmarter.pointmarket.domain.account.domain.Authority;
import com.ksmarter.pointmarket.domain.account.gql.context.AccountContextBuilder;
import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.account.repository.AccountAuthorityRepository;
import com.ksmarter.pointmarket.domain.account.repository.AccountRepository;
import com.ksmarter.pointmarket.domain.account.repository.AuthorityRepository;
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

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@DgsComponent
public class AccountFetcher {
    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;
    private final AccountAuthorityRepository accountAuthorityRepository;
    private final AccountContextBuilder accountContextBuilder;

    public AccountFetcher(AccountRepository accountRepository, AuthorityRepository authorityRepository, AccountAuthorityRepository accountAuthorityRepository, AccountContextBuilder accountContextBuilder) {
        this.accountRepository = accountRepository;
        this.authorityRepository = authorityRepository;
        this.accountAuthorityRepository = accountAuthorityRepository;
        this.accountContextBuilder = accountContextBuilder;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DgsData(parentType = DgsTypeConst.QueryResolver)
    public Connection<Account> accounts(DgsDataFetchingEnvironment dfe,
                                        @InputArgument AccountFilter filter,
                                        @InputArgument Integer first,
                                        @InputArgument String after) {

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

    @DgsData(parentType = DgsConstants.ACCOUNT.TYPE_NAME, field = DgsConstants.ACCOUNT.Authorities)
    public List<Authority> authorities(DgsDataFetchingEnvironment dfe) {
        Account account = dfe.getSource();
        return accountAuthorityRepository.findById_AccountId(account.getId())
                .stream()
                .map(accountAuthority -> accountAuthority.getAuthority())
                .collect(Collectors.toList());
    }
}
