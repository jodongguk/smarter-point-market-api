package com.ksmarter.pointmarket.domain.common.domain;

import com.ksmarter.pointmarket.domain.account.adapter.AccountAdapter;
import com.ksmarter.pointmarket.domain.account.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserAuditorAware implements AuditorAware<Long> {


    @Override
    public Optional<Long> getCurrentAuditor() {

        Account account = ((AccountAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();

        return Optional.ofNullable(account.getId());
    }
}
