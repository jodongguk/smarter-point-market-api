package com.ksmarter.pointmarket.domain.institute.repository;

import com.ksmarter.pointmarket.domain.institute.domain.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

public interface InstituteRepository extends JpaRepository<Institute, Long>, JpaSpecificationExecutor<Institute> {
    Set<Institute> findByAccounts_Id_AccountId(Long accountId);
}
