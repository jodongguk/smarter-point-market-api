package com.ksmarter.pointmarket.domain.account.service;

import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.account.domain.AccountAdapter;
import com.ksmarter.pointmarket.domain.account.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class UserDetailServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    public UserDetailServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String userId) {
        Account account = accountRepository.findOneWithAuthoritiesByUserid(userId)
                .orElseThrow(() -> new UsernameNotFoundException(userId + " -> 데이터베이스에서 찾을 수 없습니다."));

        if (!account.isActivated()) throw new RuntimeException(account.getUsername() + " -> 활성화되어 있지 않습니다.");
        return new AccountAdapter(account);
    }

}
