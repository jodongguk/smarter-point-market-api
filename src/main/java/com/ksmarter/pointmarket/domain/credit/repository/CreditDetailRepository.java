package com.ksmarter.pointmarket.domain.credit.repository;

import com.ksmarter.pointmarket.domain.credit.domain.CreditDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditDetailRepository extends JpaRepository<CreditDetail, Long> {
}