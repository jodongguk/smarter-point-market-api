package com.ksmarter.pointmarket.domain.account.repository;

import com.ksmarter.pointmarket.domain.account.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

}