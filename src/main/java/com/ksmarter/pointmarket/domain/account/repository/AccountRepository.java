package com.ksmarter.pointmarket.domain.account.repository;

import com.ksmarter.pointmarket.domain.account.domain.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.Set;

public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {
    @EntityGraph(attributePaths = "authorities")
    Optional<Account> findOneWithAuthoritiesByUserid(String userid);

    Set<Account> findByChildrens_Id_ParentId(Long parentId);

    Set<Account> findByInstitutes_Id_InstituteId(Long instituteId);

    Set<Account> findByAttendInstitutes_Id_InstituteId(Long instituteId);

    Set<Account> findByFranchisors_Id_FranchisorId(Long franchisorId);

}
