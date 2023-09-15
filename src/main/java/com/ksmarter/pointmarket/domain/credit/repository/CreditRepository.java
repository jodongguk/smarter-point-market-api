package com.ksmarter.pointmarket.domain.credit.repository;

import com.ksmarter.pointmarket.domain.credit.domain.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {
}