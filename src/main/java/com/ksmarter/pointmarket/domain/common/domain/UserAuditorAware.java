package com.ksmarter.pointmarket.domain.common.domain;

import com.ksmarter.pointmarket.domain.account.adapter.AccountAdapter;
import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.account.service.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserAuditorAware implements AuditorAware<Account> {

    private final UserDetailServiceImpl userDetailService;

    @Override
    public Optional<Account> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (null == authentication || !authentication.isAuthenticated() || isAnonymous(authentication.getAuthorities())) {
            return null;
        }
        User user = (User) authentication.getPrincipal();

        AccountAdapter userDetails = (AccountAdapter) userDetailService.loadUserByUsername(user.getUsername());

        return Optional.ofNullable(userDetails.getAccount());
    }

    private boolean isAnonymous(Collection<? extends GrantedAuthority> list) {
        return list.stream().anyMatch(grantedAuthority -> StringUtils.equals(grantedAuthority.getAuthority(), "ROLE_ANONYMOUS"));
    }
}
