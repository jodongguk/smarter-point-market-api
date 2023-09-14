package com.ksmarter.pointmarket.domain.franchisor.repository;

import com.ksmarter.pointmarket.domain.franchisor.domain.Franchisor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FranchisorRepository extends JpaRepository<Franchisor, Long> {
    Set<Franchisor> findByAccounts_Id_FranchisorId(Long franchisorId);


}
