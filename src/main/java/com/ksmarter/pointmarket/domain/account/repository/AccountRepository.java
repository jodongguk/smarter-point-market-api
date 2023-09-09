package com.ksmarter.pointmarket.domain.account.repository;

import com.ksmarter.pointmarket.domain.account.domain.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @EntityGraph(attributePaths = "authorities")
    Optional<Account> findOneWithAuthoritiesByUserid(String userid);
}
