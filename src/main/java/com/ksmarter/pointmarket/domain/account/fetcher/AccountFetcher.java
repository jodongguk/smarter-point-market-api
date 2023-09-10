package com.ksmarter.pointmarket.domain.account.fetcher;

import com.ksmarter.pointmarket.constants.DgsTypeConst;
import com.ksmarter.pointmarket.domain.account.context.AccountContextBuilder;
import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.account.repository.AccountRepository;
import com.ksmarter.pointmarket.generated.types.AccountFilter;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import graphql.relay.Connection;
import graphql.relay.SimpleListConnection;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

@DgsComponent
public class AccountFetcher {

    private final AccountRepository accountRepository;
    private final AccountContextBuilder accountContextBuilder;

    public AccountFetcher(AccountRepository accountRepository, AccountContextBuilder accountContextBuilder) {
        this.accountRepository = accountRepository;
        this.accountContextBuilder = accountContextBuilder;
    }

    @Secured("ROLE_ADMIN")
    @DgsData(parentType = DgsTypeConst.QueryResolver)
    public Connection<Account> accounts(DgsDataFetchingEnvironment dfe,
                                        @InputArgument AccountFilter filter,
                                        @InputArgument Integer first,
                                        @InputArgument String after) {

        List<Account> accounts = accountRepository.findAll();
        accountContextBuilder.withAccounts(accounts).build();
        return new SimpleListConnection<>(accounts).get(dfe);
    }

    @Secured("ROLE_ADMIN")
    @DgsData(parentType = DgsTypeConst.QueryResolver)
    public Account account(DgsDataFetchingEnvironment dfe, @InputArgument("id") Long id) {
        return accountRepository.findById(id).orElseThrow(DgsEntityNotFoundException::new);
    }

}
