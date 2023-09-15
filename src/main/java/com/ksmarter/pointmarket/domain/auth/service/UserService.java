package com.ksmarter.pointmarket.domain.auth.service;

import com.ksmarter.pointmarket.domain.account.repository.AccountRepository;
import com.ksmarter.pointmarket.generated.types.InputJoinAccount;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final AccountRepository accountRepository;

    public UserService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public void joinByInputJoinAccount(InputJoinAccount inputJoinAccount) {
        
    }
}
