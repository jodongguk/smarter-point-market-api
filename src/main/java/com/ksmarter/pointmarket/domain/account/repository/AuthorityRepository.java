package com.ksmarter.pointmarket.domain.account.repository;

import com.ksmarter.pointmarket.domain.account.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

    Set<Authority> findByAccountAuthorities_Account_Id(Long id);


}