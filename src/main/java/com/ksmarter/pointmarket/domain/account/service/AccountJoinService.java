package com.ksmarter.pointmarket.domain.account.service;

import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.account.repository.AccountRepository;
import graphql.GraphQLException;
import org.springframework.stereotype.Service;

@Service
public class AccountJoinService {

    private final AccountRepository accountRepository;

    public AccountJoinService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public Account getUserByUserId(String userid) throws Exception{
        return accountRepository.findOneWithAuthoritiesByUserid(userid).orElseThrow(GraphQLException::new);
    }
}
