package com.ksmarter.pointmarket.domain.account.repository;

import com.ksmarter.pointmarket.domain.account.domain.Account;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;

public interface AccountRepository {
    @EntityGraph(attributePaths = "authorities")
    Optional<Account> findOneWithAuthoritiesByUsername(String username);
}
