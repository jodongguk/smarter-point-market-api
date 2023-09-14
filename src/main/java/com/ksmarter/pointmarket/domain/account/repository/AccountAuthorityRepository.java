package com.ksmarter.pointmarket.domain.account.repository;

import com.ksmarter.pointmarket.domain.account.domain.AccountAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface AccountAuthorityRepository extends JpaRepository<AccountAuthority, AccountAuthority.AccountAuthorityId> {
    List<AccountAuthority> findById_AccountId(Long accountId);

    List<AccountAuthority> findById_AccountIdIn(Collection<Long> accountIds);
}