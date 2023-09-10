package com.ksmarter.pointmarket.domain.account.gql.context;


import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.netflix.graphql.dgs.context.DgsCustomContextBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountContextBuilder implements DgsCustomContextBuilder<AccountContext> {

    private List<Account> accounts;

    public AccountContextBuilder withAccounts(List<Account> accounts) {
        this.accounts = accounts;
        return this;
    }

    @Override
    public AccountContext build() {
        return new AccountContext(accounts);
    }
}
