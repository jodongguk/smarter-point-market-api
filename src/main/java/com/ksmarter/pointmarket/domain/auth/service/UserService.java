package com.ksmarter.pointmarket.domain.auth.service;

import com.ksmarter.pointmarket.domain.account.domain.*;
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
import java.util.*;

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

        if(inputJoinAccount.getBirthDate() != null) {
            accountBuilder.birthDate(inputJoinAccount.getBirthDate());
        }
        accountBuilder.activated(true);

        Account account = accountBuilder.build();

        Set<AccountInstitute> accountInstitutes = new HashSet<>();
        Optional.ofNullable(inputJoinAccount.getInstitutes()).orElseGet(Collections::emptyList).forEach(inputInstitute -> {
            Institute institute = instituteRepository.findById(Long.parseLong(inputInstitute.getId())).orElseThrow();
            accountInstitutes.add(AccountInstitute.builder().institute(institute).build());
        });

        Set<AccountChildren> accountChildren = new HashSet<>();
        Optional.ofNullable(inputJoinAccount.getChildren()).orElseGet(Collections::emptyList).forEach(child -> {
            Account findChild = accountRepository.findById(Long.parseLong(child.getId())).orElseThrow();
            accountChildren.add(AccountChildren.builder().children(findChild).build());
        });

        // 권한부여
        Set<AccountAuthority> accountAuthorities = new HashSet<>();

        if(accountInstitutes.size() > 0) {
            Authority roleInstitute = authorityRepository.findById("ROLE_INSTITUTE").orElseThrow();
            accountAuthorities.add(AccountAuthority.builder().authority(roleInstitute).build());
        }

        if(accountChildren.size() > 0) {
            Authority roleInstitute = authorityRepository.findById("ROLE_PARENT").orElseThrow();
            accountAuthorities.add(AccountAuthority.builder().authority(roleInstitute).build());
        }

        account.addAuthorities(accountAuthorities);
        account.addInstitutes(accountInstitutes);
        account.addChildrens(accountChildren);

        return accountRepository.save(account);
    }

    public Account addByChildren(InputJoinAccount inputJoinAccount) {

        Account.AccountBuilder accountBuilder = Account.builder()
                //.userid(inputJoinAccount.getUserid())
                //.password(passwordEncoder.encode(inputJoinAccount.getPassword()))
                .name(inputJoinAccount.getName())
                .phoneNumber(inputJoinAccount.getPhoneNumber())
                .birthDate(inputJoinAccount.getBirthDate())
                .activated(true);

        accountBuilder.userid(UUID.randomUUID().toString());

        return accountRepository.save(accountBuilder.build());
    }
}
