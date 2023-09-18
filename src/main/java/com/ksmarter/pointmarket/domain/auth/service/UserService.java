package com.ksmarter.pointmarket.domain.auth.service;

import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.account.domain.AccountAuthority;
import com.ksmarter.pointmarket.domain.account.domain.Authority;
import com.ksmarter.pointmarket.domain.account.repository.AccountRepository;
import com.ksmarter.pointmarket.domain.account.repository.AuthorityRepository;
import com.ksmarter.pointmarket.generated.types.InputJoinAccount;
import io.micrometer.common.util.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;

    public UserService(PasswordEncoder passwordEncoder, AccountRepository accountRepository, AuthorityRepository authorityRepository) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
        this.authorityRepository = authorityRepository;
    }


    @Transactional
    public Account joinByInputJoinAccount(InputJoinAccount inputJoinAccount) {

        Account.AccountBuilder accountBuilder = Account.builder()
                .userid(inputJoinAccount.getUserid())
                .password(passwordEncoder.encode(inputJoinAccount.getPassword()))
                .name(inputJoinAccount.getName())
                .phoneNumber(inputJoinAccount.getPhoneNumber());

        if(StringUtils.isNotEmpty(inputJoinAccount.getBirthDate())) {
            accountBuilder.birthDate(LocalDate.parse(inputJoinAccount.getBirthDate()));
        }
        accountBuilder.activated(true);

        Account account = accountBuilder.build();
        account.addAuthorities(Set.of(AccountAuthority.builder().authority(Authority.builder().authorityName("ROLE_INSTITUTE").build()).build()));

        return accountRepository.save(account);
    }
}
