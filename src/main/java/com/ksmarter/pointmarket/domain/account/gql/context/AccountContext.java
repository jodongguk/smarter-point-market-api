package com.ksmarter.pointmarket.domain.account.gql.context;

import com.ksmarter.pointmarket.domain.account.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class AccountContext {
    private List<Account> accounts = new ArrayList<>();
}
