package com.ksmarter.pointmarket.domain.account.service;

import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.account.domain.AccountAdapter;
import com.ksmarter.pointmarket.domain.account.dto.AccountRequest;
import com.ksmarter.pointmarket.domain.account.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AccountJoinService {

    private final AccountRepository accountRepository;

    public AccountJoinService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /*public UserDetails join(AccountRequest.Join dto) {



        Account account = accountRepository.save(accountInput);
        return new AccountAdapter(account);
    }*/
}
