package com.ksmarter.pointmarket.domain.auth.service;

import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.account.domain.AccountAuthority;
import com.ksmarter.pointmarket.domain.account.domain.AccountInstitute;
import com.ksmarter.pointmarket.domain.account.domain.Authority;
import com.ksmarter.pointmarket.domain.account.repository.AccountRepository;
import com.ksmarter.pointmarket.domain.account.repository.AuthorityRepository;
import com.ksmarter.pointmarket.domain.institute.domain.Institute;
import com.ksmarter.pointmarket.domain.institute.repository.InstituteRepository;
import com.ksmarter.pointmarket.generated.types.InputJoinAccount;
import io.micrometer.common.util.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;
    private final InstituteRepository instituteRepository;

    public UserService(PasswordEncoder passwordEncoder, AccountRepository accountRepository, AuthorityRepository authorityRepository, InstituteRepository instituteRepository) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
        this.authorityRepository = authorityRepository;
        this.instituteRepository = instituteRepository;
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

        Authority roleInstitute = authorityRepository.findById("ROLE_INSTITUTE").orElseThrow();

        account.addAuthorities(Set.of(
                AccountAuthority.builder().authority(roleInstitute).build()
        ));

        Set<AccountInstitute> accountInstitutes = new HashSet<>();
        Optional.ofNullable(inputJoinAccount.getInstitutes()).orElseGet(Collections::emptyList).forEach(inputInstitute -> {

            Institute institute = instituteRepository.findById(Long.parseLong(inputInstitute.getId())).orElseThrow();
            accountInstitutes.add(AccountInstitute.builder().institute(institute).build());
        });

        account.addInstitutes(accountInstitutes);

        return accountRepository.save(account);
    }

}
