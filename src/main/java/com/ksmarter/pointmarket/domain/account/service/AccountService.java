package com.ksmarter.pointmarket.domain.account.service;

import com.ksmarter.pointmarket.domain.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


}
