package com.ksmarter.pointmarket.domain.institute.repository;

import com.ksmarter.pointmarket.domain.institute.domain.Institute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface InstituteRepository extends JpaRepository<Institute, Long> {
    Set<Institute> findByAccounts_Id_AccountId(Long accountId);
}
